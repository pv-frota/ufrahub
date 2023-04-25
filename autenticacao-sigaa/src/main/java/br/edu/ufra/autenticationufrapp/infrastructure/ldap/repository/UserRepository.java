package br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository("LdapUserRepository")
public interface UserRepository<T extends LdapUser> extends LdapRepository<T> {
    T findByUsername(String username);
}
