package br.edu.ufra.ufrahub.integracao.sigaa.domain.repository;

import br.edu.ufra.ufrahub.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    File findFirstByUsernameAndType(String username, FileTypeEnum type);
}
