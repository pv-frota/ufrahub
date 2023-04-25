package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;
import br.edu.ufra.integracao.sigaa.domain.model.File;
import br.edu.ufra.integracao.sigaa.domain.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class FileService {
    private final FileRepository repository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.repository = fileRepository;
    }

    public byte[] getFile(String username, FileTypeEnum type) {
        File file = repository.findFirstByUsernameAndType(username, type);
        if(file != null) {
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.add(Calendar.HOUR_OF_DAY, -2);
            Date twoHoursAgo = cal.getTime();

            if (file.getSaved().after(twoHoursAgo)) {
                return file.getData();
            } else {
                //TODO integração sigaa para pegar o relatório.
                return null;
            }
        }
        return null;
    }
}
