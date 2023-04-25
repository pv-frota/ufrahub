package br.edu.ufra.autenticationufrapp.domain.service;

import br.edu.ufra.autenticationufrapp.infrastructure.ldap.model.LdapGroup;
import br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class GroupService {
    @Value("${ldap.group-layout}")
    private String layout;

    @SuppressWarnings("rawtypes")
    private final GroupRepository repository;

    @SuppressWarnings("rawtypes")
    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public List<String> getUserGroups(String username) throws InvalidNameException {
        Name cn = new CompositeName(layout.replace("{}", username));
        List<LdapGroup> groups = repository.findAll();

        return groups.stream()
                .filter(g -> g.getMembers().stream().filter(m -> m.toString().equals(cn.toString())).count() > 0)
                .map((LdapGroup::getName))
                .collect(Collectors.toList());
    }
}
