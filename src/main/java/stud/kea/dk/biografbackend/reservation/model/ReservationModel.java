package stud.kea.dk.biografbackend.reservation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
/*
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private CustomerModel customer;

 */

    @ManyToOne
    @JoinColumn(name="showtime_id", referencedColumnName = "id", nullable = false)
    private ShowtimeModel showtime;
}
