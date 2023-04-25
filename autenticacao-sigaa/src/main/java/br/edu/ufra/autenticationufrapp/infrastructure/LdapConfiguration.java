package br.edu.ufra.autenticationufrapp.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {
    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.partitionSuffix}")
    private String suffix;

    @Value("${ldap.principal}")
    private String principal;

    @Value("${ldap.password}")
    private String password;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl(url);
        contextSource.setBase(suffix);
        contextSource.setUserDn(principal);
        contextSource.setPassword(password);

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(LdapContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
}
