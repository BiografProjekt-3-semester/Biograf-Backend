package stud.kea.dk.biografbackend.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;

@Component
public class InitData {
    @Autowired
    CustomerRepository customerRepository;
}
