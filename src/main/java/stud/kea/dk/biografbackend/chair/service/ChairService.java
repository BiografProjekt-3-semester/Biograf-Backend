package stud.kea.dk.biografbackend.chair.service;

import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;

import java.util.List;

// @Service
public class ChairService {

    final private ChairRepository chairRepository;

    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public ChairModel saveChair (ChairModel chairModel) {
        return chairRepository.save(chairModel);
    }

    public List<ChairModel> getAllChairs() {
        return chairRepository.findAll();
    }
}
