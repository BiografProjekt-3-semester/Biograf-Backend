package stud.kea.dk.biografbackend.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.customer.service.CustomerService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Hent alle kunder
    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        List<CustomerModel> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Hent en kunde ved ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable int id) {
        Optional<CustomerModel> customer = customerService.findById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Opret en ny kunde
    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel newCustomer) {
        CustomerModel createdCustomer = customerService.createCustomer(newCustomer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // Opdater en eksisterende kunde
    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(
            @PathVariable int id,
            @RequestBody CustomerModel updatedCustomer) {
        Optional<CustomerModel> updated = customerService.updateCustomer(id, updatedCustomer);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Slet en kunde
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        boolean isDeleted = customerService.deleteById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }
}
