package br.edu.ufra.autenticationufrapp.infrastructure.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LdapProperties {
    @Value("${ldap.user-search-base}")
    private String userSearchBase;

    @Value("${ldap.user-search-filter}")
    private String userSearchFilter;

    @Value("${ldap.group-search-base}")
    private String groupSearchBase;

    @Value("${ldap.group-search-filter}")
    private String groupSearchFilter;

    @Value("${ldap.group-role-attribute}")
    private String groupRoleAttribute;

    @Value("${ldap.password-attribute}")
    private String passwordAttribute;

    public String getUserSearchBase() {
        return userSearchBase;
    }

    public String getUserSearchFilter() {
        return userSearchFilter;
    }

    public String getGroupSearchBase() {
        return groupSearchBase;
    }

    public String getGroupSearchFilter() {
        return groupSearchFilter;
    }

    public String getGroupRoleAttribute() {
        return groupRoleAttribute;
    }

    public String getPasswordAttribute() {
        return passwordAttribute;
    }
}
