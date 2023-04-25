package br.edu.ufra.autenticationufrapp.domain.dev.repository;

import br.edu.ufra.autenticationufrapp.domain.dev.model.DevGroup;
import br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository.GroupRepository;
import br.edu.ufra.commonsufrahub.annotation.Development;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
@Primary
@Development
public interface DevGroupRepository extends GroupRepository<DevGroup> {
    List<DevGroup> findGroupsByMembersContains(Name dn);
}
