package br.edu.ufra.autenticationufrapp.domain.dev.model;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapGroup;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Set;

@Entry(objectClasses = {"top", "groupOfUniqueNames"})
public final class DevGroup implements LdapGroup {

    @Id
    private Name dn;

    @Attribute(name="cn")
    @DnAttribute("cn")
    private String name;

    @Attribute(name="uniqueMember")
    private Set<Name> members;

    public Name getDn() {
        return dn;
    }

    public Set<Name> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

}