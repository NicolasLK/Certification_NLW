package com.rocketseat.certification_NLW.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {
    
    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/certification_NLW");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1804");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    public void run(String... args) {
        executeSqlFile("src/main/resources/create.sql");
    }

    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizada com sucesso");
        } catch (IOException e) {
           System.err.println("Erro ao executar arquivo" + e.getMessage());
        }
        
    }
}
