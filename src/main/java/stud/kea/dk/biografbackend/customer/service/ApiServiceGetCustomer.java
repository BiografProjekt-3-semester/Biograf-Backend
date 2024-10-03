package stud.kea.dk.biografbackend.customer.service;

import stud.kea.dk.biografbackend.customer.model.CustomerModel;

import java.util.List;

public interface ApiServiceGetCustomer {
    List<CustomerModel> getCustomers();
}
