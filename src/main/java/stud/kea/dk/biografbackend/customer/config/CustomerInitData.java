package stud.kea.dk.biografbackend.customer.config;

import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;


public class CustomerInitData {

    final private CustomerRepository customerRepository;

    public CustomerInitData (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
