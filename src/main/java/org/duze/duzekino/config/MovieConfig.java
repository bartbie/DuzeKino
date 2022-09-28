package org.duze.duzekino.config;


import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Movie;
import org.duze.duzekino.model.PG;
import org.duze.duzekino.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MovieConfig {

    @Bean
    public CommandLineRunner commandLineRunner(MovieService service) {
        return args -> {};
    }

}
