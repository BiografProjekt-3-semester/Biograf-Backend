package stud.kea.dk.biografbackend.chair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;

import java.util.List;

// @Service
public class ChairService {

    // @Autowired
    private ChairRepository chairRepository;

    public ChairModel saveChair (ChairModel chairModel) {
        return chairRepository.save(chairModel);
    }

    public List<ChairModel> getAllChairs() {
        return chairRepository.findAll();
    }
}
