package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.integracao.sigaa.AbstractTestContainer;
import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.integracao.sigaa.domain.model.File;
import br.edu.ufra.integracao.sigaa.domain.repository.FileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FileServiceTest extends AbstractTestContainer {

    private final FileRepository repository;
    private final FileService underTest;

    @Autowired
    FileServiceTest(FileRepository repository) {
        this.repository = repository;
        this.underTest = new FileService(repository);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void getFileShouldReturn() {
        //given
        String selectedUser = "pv_frota";
        FileTypeEnum selectedFile = FileTypeEnum.HISTORICO;
        Date now = new Date();
        File vinculo = new File(1, selectedUser, FileTypeEnum.VINCULO, now, new byte[]{});
        File historico = new File(2, selectedUser, selectedFile, now, new byte[]{});
        repository.save(vinculo);
        repository.save(historico);
        //when
        byte[] expected = underTest.getFile(selectedUser, selectedFile);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void getFileShouldReturnNull() {
        //given
        String selectedUser = "pv_frota";
        FileTypeEnum selectedFile = FileTypeEnum.HISTORICO;
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.HOUR_OF_DAY, -3);
        Date threeHoursAgo = cal.getTime();
        File vinculo = new File(1, selectedUser, FileTypeEnum.VINCULO, now, new byte[]{});
        File historico = new File(2, selectedUser, selectedFile, threeHoursAgo, new byte[]{});
        repository.save(vinculo);
        repository.save(historico);
        //when
        byte[] expected = underTest.getFile(selectedUser, selectedFile);
        //then
        assertThat(expected).isNull();
    }
}