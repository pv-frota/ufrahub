package br.edu.ufra.integracao.sigaa.domain.service;

import br.edu.ufra.integracao.sigaa.domain.model.User;
import br.edu.ufra.integracao.sigaa.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository fileRepository) {
        this.repository = fileRepository;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
