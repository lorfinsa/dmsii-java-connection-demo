package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
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

        @Value("${spring.datasource.hikari.connection-test-query}")
        String testQuery;

        @Override
        public void run(String... args) throws Exception {
            if (args.length == 0) {
                throw new IllegalArgumentException("Debe haber un parametro con la query a ejecutar.");
            }
            long inicio = 0;
            boolean medirEjecucion = args.length == 2 ? "true".equals(args[1]) : false;
            if (medirEjecucion) {
                jdbcTemplate.execute(testQuery);
                inicio = System.currentTimeMillis();
                System.out.println("INICIO DE LA CONSULTA: " + inicio);
            }
            
            List<Map<String, Object>> resultado = jdbcTemplate.queryForList(args[0]);
            
            if (medirEjecucion) {
                long fin = System.currentTimeMillis();
                System.out.println("FIN DE LA CONSULTA: " + fin);
                System.out.println(String.format("TIEMPO DE EJECUCION: %d ms", (fin - inicio)));
            }
            System.out.println("CANTIDAD DE REGISTROS: " + resultado.size());
            System.out.println("RESULTADO: " + new ObjectMapper().writeValueAsString(resultado));
        }
    }

}
