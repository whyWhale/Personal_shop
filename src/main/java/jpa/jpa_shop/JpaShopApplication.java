package jpa.jpa_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
public class JpaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaShopApplication.class, args);
    }

}
