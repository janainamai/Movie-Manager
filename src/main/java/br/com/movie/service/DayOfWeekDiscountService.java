package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.DayOfWeekDiscount;
import br.com.movie.repository.DayOfWeekDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayOfWeekDiscountService {

    @Autowired
    private DayOfWeekDiscountRepository repository;

    private static final String DAYOFWEEK_NOT_FOUND = "Day of week discount not found";

    public List<DayOfWeekDiscount> list() {
        return repository.findAll();
    }

    public DayOfWeekDiscount findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException(DAYOFWEEK_NOT_FOUND));
    }

    public DayOfWeekDiscount findByDayOfWeek(String dayOfWeek) {
        return repository.findByDayOfWeekIgnoreCase(dayOfWeek)
                .orElseThrow(() -> new BadRequestException(DAYOFWEEK_NOT_FOUND));
    }

    public DayOfWeekDiscount save(DayOfWeekDiscount dayOfWeekDiscount) {
        Optional<DayOfWeekDiscount> optional = repository.findByDayOfWeekIgnoreCase(dayOfWeekDiscount.getDayOfWeek());

        if(optional.isPresent()) {
            throw new BadRequestException("There is already a day of week discount with this description");
        }

        return repository.save(dayOfWeekDiscount);
    }

    public DayOfWeekDiscount replace(DayOfWeekDiscount discountToSave) {
        Optional<DayOfWeekDiscount> optional = repository.findById(discountToSave.getId());
        if (optional.isPresent()) {
            return repository.save(discountToSave);
        } else {
            throw new BadRequestException(DAYOFWEEK_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new BadRequestException(DAYOFWEEK_NOT_FOUND);
        }
    }
}