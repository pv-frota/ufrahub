package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.integracao.sigaa.domain.model.Message;
import br.edu.ufra.integracao.sigaa.domain.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository fileRepository) {
        this.repository = fileRepository;
    }

    public void save(Message entity) {
        repository.save(entity);
    }
}
