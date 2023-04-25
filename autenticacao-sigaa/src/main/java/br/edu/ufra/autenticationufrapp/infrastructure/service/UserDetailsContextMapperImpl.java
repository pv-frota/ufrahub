package br.edu.ufra.autenticationufrapp.infrastructure.service;

import br.edu.ufra.autenticationufrapp.domain.service.GroupService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import javax.naming.InvalidNameException;
import java.util.*;

@Configuration
@Primary
public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {

    private final GroupService service;

    public UserDetailsContextMapperImpl(GroupService service) {
        this.service = service;
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        Set<GrantedAuthority> authority = new HashSet<>();
        List<String> groupNames = new ArrayList<>();
        String cn = ctx.getStringAttribute("cn");

        try {
            groupNames = service.getUserGroups(username);
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        for(String name: groupNames) {
            String role = "ROLE_" + name.toUpperCase();
            authority.add(new SimpleGrantedAuthority(role));
        }

        return new UserDetailsImpl(username, cn, false, false, false, false, authority);
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

    }
}
