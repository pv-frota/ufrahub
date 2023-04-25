package br.edu.ufra.autenticationufrapp.infrastructure.ldap.model;

import javax.naming.Name;
import java.util.Set;

public interface LdapGroup {
    Name getDn();
    Set<Name> getMembers();
    String getName();
}
