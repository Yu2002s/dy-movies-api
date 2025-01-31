package xyz.jdynb.dymovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DyMoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyMoviesApplication.class, args);
    }

}
