package org.example.springprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracApplication.class, args);
    }

}
