package org.youjhin.springhw5notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringHw5NotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHw5NotesApplication.class, args);
    }

}
