package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SqlServerConnectionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlServerConnectionDemoApplication.class, args);
    }

    @Component
    public static class DataLoader implements CommandLineRunner {

        @Autowired
        private JdbcTemplate jdbcTemplate;
        
        @Value("${db.perfil}")
        private String perfil;

        @Override
        public void run(String... strings) throws Exception {
            System.out.println(jdbcTemplate.queryForList("SELECT TOP 3 * FROM " + perfil + ".PASDO001"));
        }
    }

}
