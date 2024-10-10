package stud.kea.dk.biografbackend.reservation.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.biografbackend.customer.model.CustomerModel;
import stud.kea.dk.biografbackend.customer.repository.CustomerRepository;
import stud.kea.dk.biografbackend.reservation.model.ReservationModel;
import stud.kea.dk.biografbackend.reservation.repository.ReservationRepository;
import stud.kea.dk.biografbackend.showtime.model.ShowtimeModel;
import stud.kea.dk.biografbackend.showtime.repository.ShowtimeRepository;

import java.util.Optional;

@Component
@Order(6)
public class ReservationInitData implements CommandLineRunner {
    private CustomerRepository customerRepository;

    private ShowtimeRepository showtimeRepository;
    final private ReservationRepository repository;

    public ReservationInitData(ReservationRepository repository, ShowtimeRepository showtimeRepository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.showtimeRepository =showtimeRepository;
        this.customerRepository = customerRepository;

    }


    @Override
    public void run(String... args) throws Exception {


        for (int i = 1; i < 3; i++) {


            // Hent en kunde fra customerRepository
            Optional<CustomerModel> optionalCustomer = customerRepository.findById(i); // Forudsætter en kunde med id 1
            if (optionalCustomer.isEmpty()) {
                System.out.println("Customer with ID "+ i +" not found");
                return;
            }
            CustomerModel customer = optionalCustomer.get();


            // Hent en showtime fra showtimeRepository
            Optional<ShowtimeModel> optionalShowtime = showtimeRepository.findById(i); // Forudsætter en showtime med id 1
            if (optionalShowtime.isEmpty()) {
                System.out.println("Showtime with ID "+ i +" not found");
                return;
            }

            ShowtimeModel showtime = optionalShowtime.get();

            // Opret en ny reservation
            ReservationModel reservation = new ReservationModel();
            reservation.setPrice(100); // sæt prisen for reservationen
            //reservation.setCustomer(customer); // Sæt kunden i reservationen
            reservation.setShowtime(showtime); // Sæt showtime i reservationen

            // Gem reservationen
            repository.save(reservation);

        }
        System.out.println("Reservation created successfully.");
    }
}