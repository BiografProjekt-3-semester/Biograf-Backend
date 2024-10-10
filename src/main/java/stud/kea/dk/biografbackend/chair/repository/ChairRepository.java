package stud.kea.dk.biografbackend.chair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.chair.model.ChairModel;

import java.util.List;

public interface ChairRepository extends JpaRepository<ChairModel, Integer> {
    List<ChairModel> findByTheaterId(int theaterId);
}
