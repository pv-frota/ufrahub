package br.edu.ufra.ufrahub.integracao.sigaa.domain.repository;

import br.edu.ufra.ufrahub.integracao.sigaa.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
