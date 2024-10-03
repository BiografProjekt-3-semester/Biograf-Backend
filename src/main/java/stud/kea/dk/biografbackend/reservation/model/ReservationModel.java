package stud.kea.dk.biografbackend.reservation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int price;
   /* int id.Customer
    int id.ShowTime
    @ManyToOne
    @JoinColumn(name="CustomerModel", referencedColumnName = "id")
    CustomerModel customer;

    @ManyToOne
    @JoinColumn(name="ShowtimeModel", referencedColumnName = "id")
    ShowtimeModel showtime;
*/

}
