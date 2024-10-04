package stud.kea.dk.biografbackend.bookedChair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.biografbackend.bookedChair.model.BookedChairModel;

@Repository
public interface BookedChairRepository extends JpaRepository<BookedChairModel, Integer> {
}
