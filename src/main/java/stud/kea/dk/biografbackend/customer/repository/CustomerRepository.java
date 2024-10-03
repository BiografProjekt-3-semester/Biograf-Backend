package stud.kea.dk.biografbackend.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

}
