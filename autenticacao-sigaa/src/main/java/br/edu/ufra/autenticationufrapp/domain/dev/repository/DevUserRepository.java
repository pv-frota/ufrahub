package br.edu.ufra.autenticationufrapp.domain.dev.repository;

import br.edu.ufra.autenticationufrapp.domain.dev.model.DevUser;
import br.edu.ufra.autenticationufrapp.infrastructure.ldap.repository.UserRepository;
import br.edu.ufra.commonsufrahub.annotation.Development;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Development
public interface DevUserRepository extends UserRepository<DevUser> {
    DevUser findByUsername(String username);
}
