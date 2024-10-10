package stud.kea.dk.biografbackend.customer.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> findById(int id) {
        return customerRepository.findById(id);
    }

    public CustomerModel createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public Optional<CustomerModel> updateCustomer(int id, CustomerModel updatedCustomer) {
        Optional<CustomerModel> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            CustomerModel existingCustomer = customerOptional.get();
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAge(updatedCustomer.getAge());

            return Optional.of(customerRepository.save(existingCustomer));
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
