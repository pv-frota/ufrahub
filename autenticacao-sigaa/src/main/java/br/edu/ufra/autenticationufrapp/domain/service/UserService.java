package br.edu.ufra.autenticationufrapp.domain.service;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapUser;
import br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("rawtypes")
@Service("LdapUserService")
public class UserService {
    @Value("${ldap.user-layout}")
    private String layout;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public LdapUser findByUsername(String username) throws InvalidNameException {
        Name name = new CompositeName(layout.replace("{}", username));
        List<Name> id = Collections.singletonList(name);
        List<LdapUser> user = repository.findAllById(id);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o login: " + name);
        } else {
            return  user.get(0);
        }
    }
}
