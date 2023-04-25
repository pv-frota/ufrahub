package br.edu.ufra.integracao.sigaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.edu.ufra.integracao.sigaa", "br.edu.ufra.autenticationufrapp"})
@EntityScan(basePackages = {"br.edu.ufra.integracao.sigaa", "br.edu.ufra.autenticationufrapp"})
@EnableJpaRepositories("br.edu.ufra.integracao.sigaa")
@EnableLdapRepositories("br.edu.ufra.autenticationufrapp")
public class ApiIntegracaoSigaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiIntegracaoSigaaApplication.class, args);
    }

}
