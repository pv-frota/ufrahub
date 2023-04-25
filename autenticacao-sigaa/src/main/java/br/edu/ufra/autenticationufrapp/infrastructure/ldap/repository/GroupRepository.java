package br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapGroup;
import org.springframework.data.ldap.repository.LdapRepository;

import javax.naming.Name;
import java.util.List;

public interface GroupRepository<T extends LdapGroup> extends LdapRepository<T> {
    List<T> findGroupsByMembersContains(Name dn);
}
