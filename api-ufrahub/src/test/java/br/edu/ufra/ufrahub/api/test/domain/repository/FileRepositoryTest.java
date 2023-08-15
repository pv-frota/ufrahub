package br.edu.ufra.ufrahub.api.test.domain.repository;

import br.edu.ufra.ufrahub.api.test.MySQLTestContainer;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.model.File;
import br.edu.ufra.ufrahub.integracao.sigaa.domain.repository.FileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FileRepositoryTest extends MySQLTestContainer {

    private final FileRepository underTest;

    @Autowired
    public FileRepositoryTest(FileRepository underTest) {
        this.underTest = underTest;
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindFirstFileByUsernameAndType() {
        //given
        String selectedUser = "pv_frota";
        FileTypeEnum selectedFile = FileTypeEnum.HISTORICO;
        Date now = new Date();
        File vinculo = new File(1, selectedUser, FileTypeEnum.VINCULO, now, new byte[]{});
        File historico = new File(2, selectedUser, selectedFile, now, new byte[]{});
        underTest.save(vinculo);
        underTest.save(historico);
        //when
        File expected = underTest.findFirstByUsernameAndType(selectedUser, selectedFile);
        //then
        assertThat(expected.getId()).isEqualTo(historico.getId());
    }
}