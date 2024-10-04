package stud.kea.dk.biografbackend.chair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.biografbackend.chair.model.ChairModel;

public interface ChairRepository extends JpaRepository<ChairModel, Integer> {
}
