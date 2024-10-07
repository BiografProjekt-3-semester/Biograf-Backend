package stud.kea.dk.biografbackend.bookedChair.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.bookedChair.model.BookedChairModel;
import stud.kea.dk.biografbackend.bookedChair.repository.BookedChairRepository;
import stud.kea.dk.biografbackend.chair.model.ChairModel;
import stud.kea.dk.biografbackend.chair.repository.ChairRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookedChairService {


    final private BookedChairRepository bookedChairRepository;
    final private ChairRepository chairRepository;

    public BookedChairService(BookedChairRepository bookedChairRepository, ChairRepository chairRepository) {
        this.bookedChairRepository = bookedChairRepository;
        this.chairRepository = chairRepository;
    }

    public List<BookedChairModel> findAll() {
        return bookedChairRepository.findAll();
    }

    public Optional<BookedChairModel> findById(int id) {
        return bookedChairRepository.findById(id);
    }

    public BookedChairModel save(BookedChairModel bookedChair) {
        ChairModel chair = bookedChair.getChair();

        chair.setAvailable(false);
        chairRepository.save(chair);

        return bookedChairRepository.save(bookedChair);
    }

    public void deleteById(int id) {
        Optional<BookedChairModel> bookedChair = bookedChairRepository.findById(id);

        if (bookedChair.isPresent()) {
            ChairModel chair = bookedChair.get().getChair();
            chair.setAvailable(true);
            chairRepository.save(chair);
        }
        bookedChairRepository.deleteById(id);
    }
}
