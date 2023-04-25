package br.edu.ufra.autenticationufrapp.infrastructure.service;

import br.edu.ufra.autenticationufrapp.domain.service.GroupService;
import br.edu.ufra.autenticationufrapp.domain.service.UserService;
import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final GroupService groupService;

    public UserDetailsServiceImpl(@Qualifier("LdapUserService") UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> roles = new ArrayList<>();
        LdapUser user = null;
        try {
            user = userService.findByUsername(username);
            roles = groupService.getUserGroups(username);
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        return UserDetailsImpl.build(user, roles);
    }
}
