package br.edu.ufra.ufrahub.api.infrastructure.security;

import br.edu.ufra.autenticationufrapp.infrastructure.jwt.AuthEntryPointJwt;
import br.edu.ufra.autenticationufrapp.infrastructure.jwt.AuthTokenFilter;
import br.edu.ufra.autenticationufrapp.infrastructure.properties.LdapProperties;
import br.edu.ufra.autenticationufrapp.infrastructure.service.UserDetailsContextMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private LdapProperties ldapProperties;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final LdapContextSource contextSource;
    private final UserDetailsContextMapperImpl userDetailsContextMapper;

    @Autowired
    public WebSecurityConfig(LdapProperties ldapProperties, AuthEntryPointJwt unauthorizedHandler, LdapContextSource contextSource, UserDetailsContextMapperImpl userDetailsContextMapper) {
        this.ldapProperties = ldapProperties;
        this.unauthorizedHandler = unauthorizedHandler;
        this.contextSource = contextSource;
        this.userDetailsContextMapper = userDetailsContextMapper;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .ldapAuthentication()
                .userDetailsContextMapper(userDetailsContextMapper)
                .userSearchBase(ldapProperties.getUserSearchBase())
                .userSearchFilter(ldapProperties.getUserSearchFilter())
                .groupSearchBase(ldapProperties.getGroupSearchBase())
                .groupRoleAttribute(ldapProperties.getGroupRoleAttribute())
                .groupSearchFilter(ldapProperties.getGroupSearchFilter())
                .contextSource(contextSource)
                .passwordCompare()
                .passwordAttribute(ldapProperties.getPasswordAttribute());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        corsConfiguration.addAllowedOrigin("http://localhost:61646");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}