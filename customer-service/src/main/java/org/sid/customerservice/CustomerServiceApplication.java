package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                               RepositoryRestConfiguration restConfiguration)
    {
        return  args -> {
            //Permet d'exposer id de micro service qui est generer imlecitement par spring-data-rest
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("Sabrine").mail("sabrine@gmail.com").build(),
                            Customer.builder().name("Madane").mail("madane@gmail.com").build(),
                            Customer.builder().name("Souad").mail("souad@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(
                    customer -> System.out.println(customer)
            );


        };

    }
}
