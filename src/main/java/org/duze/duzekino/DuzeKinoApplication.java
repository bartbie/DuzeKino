package org.duze.duzekino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DuzeKinoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DuzeKinoApplication.class, args);
    }

}
