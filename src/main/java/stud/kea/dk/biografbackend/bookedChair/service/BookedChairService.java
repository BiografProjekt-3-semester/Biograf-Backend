package stud.kea.dk.biografbackend.bookedChair.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.biografbackend.bookedChair.model.BookedChairModel;
import stud.kea.dk.biografbackend.bookedChair.repository.BookedChairRepository;

import java.util.List;

@Service
public class BookedChairService {

    private final BookedChairRepository bookedChairRepository;

    public BookedChairService(BookedChairRepository bookedChairRepository) {
        this.bookedChairRepository = bookedChairRepository;
    }

    public BookedChairModel createBookedChair(BookedChairModel bookedChairModel) {
        return bookedChairRepository.save(bookedChairModel);
    }

    public List<BookedChairModel> getBookedChairsByShowtime(int showtimeId) {
        return bookedChairRepository.findByShowtimeId(showtimeId);
    }

    public BookedChairModel getBookedChairById(int id) {
        return bookedChairRepository.findById(id).orElse(null);
    }
}
