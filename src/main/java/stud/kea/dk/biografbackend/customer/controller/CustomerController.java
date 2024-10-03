package stud.kea.dk.biografbackend.customer.controller;

import org.springframework.web.bind.annotation.RestController;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
