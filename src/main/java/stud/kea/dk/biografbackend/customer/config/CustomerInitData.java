package stud.kea.dk.biografbackend.customer.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;

@Component
@Order(5)
public class CustomerInitData implements CommandLineRunner {

    final private CustomerRepository customerRepository;

    public CustomerInitData (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

                // Opret kunde 1
                CustomerModel customer1 = new CustomerModel();
                customer1.setFirstName("John");
                customer1.setLastName("Doe");
                customer1.setEmail("john.doe@example.com");
                customer1.setAge(30);
                customerRepository.save(customer1);

                // Opret kunde 2
                CustomerModel customer2 = new CustomerModel();
                customer2.setFirstName("Jane");
                customer2.setLastName("Smith");
                customer2.setEmail("jane.smith@example.com");
                customer2.setAge(25);
                customerRepository.save(customer2);

                // Opret kunde 3
                CustomerModel customer3 = new CustomerModel();
                customer3.setFirstName("Alice");
                customer3.setLastName("Johnson");
                customer3.setEmail("alice.johnson@example.com");
                customer3.setAge(35);
                customerRepository.save(customer3);

                System.out.println("Test customers initialized.");
            }
    }
