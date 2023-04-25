package br.edu.ufra.autenticationufrapp.domain.dev.model;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapUser;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = { "person", "inetOrgPerson", "organizationalPerson", "top" })
public final class DevUser implements LdapUser {
    @Id
    private Name username;
    private @Attribute(name = "cn") String name;
    private @Attribute(name = "userPassword") String password;

    @Override
    public String getUsername() {
        return this.username.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
