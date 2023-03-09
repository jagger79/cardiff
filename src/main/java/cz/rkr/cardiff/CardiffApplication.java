package cz.rkr.cardiff;

import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {CustomerRepository.class})
@EntityScan(basePackageClasses = {Customer.class})
@EnableTransactionManagement
public class CardiffApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardiffApplication.class, args);
    }
}