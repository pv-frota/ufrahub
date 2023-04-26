package br.edu.ufra.integracao.sigaa;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@DirtiesContext
public class AbstractTestContainer {
    @Container
    private static final MySQLContainer container =
            new MySQLContainer(DockerImageName.parse("mysql"))
                    .withDatabaseName("mydb")
                    .withUsername("root")
                    .withPassword("root");

    @DynamicPropertySource
    private static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(
                        container.getJdbcUrl(),
                        container.getUsername(),
                        container.getPassword())
                .load();
        flyway.migrate();
    }
}
