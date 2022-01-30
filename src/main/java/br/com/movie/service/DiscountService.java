package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Discount;
import br.com.movie.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository repository;

    private static final String DISCOUNT_NOT_FOUND = "Discount not found";

    public List<Discount> list() {
        return repository.findAll();
    }

    public Discount findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException(DISCOUNT_NOT_FOUND));
    }

    public Discount findByDescription(String description) {
        Optional<Discount> optional = repository.findByDescriptionIgnoreCase(description);
        if (optional.isEmpty()) {
            throw new BadRequestException(DISCOUNT_NOT_FOUND);
        }

        return optional.get();
    }

    public Discount save(Discount discount) {
        Optional<Discount> optional = repository.findByDescriptionIgnoreCase(discount.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a discount with this description");
        }

        return repository.save(discount);
    }

    public Discount replace(Discount discount) {
        if (repository.existsById(discount.getId()) && isValidToReplace(discount)) {
            return repository.save(discount);
        } else {
            throw new BadRequestException(DISCOUNT_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new BadRequestException(DISCOUNT_NOT_FOUND);
        }
    }

    private boolean isValidToReplace(Discount discount) {
        Optional<Discount> optional = repository.findByDescriptionIgnoreCase(discount.getDescription());

        if (optional.isPresent() && !optional.get().getId().equals(discount.getId())) {
            throw new BadRequestException("There is already a discount with this description");
        }
        return true;
    }

}