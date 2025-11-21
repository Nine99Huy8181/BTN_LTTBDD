package iuh.fit.fashionshop_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FashionShopBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionShopBeApplication.class, args);
    }

}
