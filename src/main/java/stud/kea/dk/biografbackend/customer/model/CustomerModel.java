package stud.kea.dk.biografbackend.customer.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
