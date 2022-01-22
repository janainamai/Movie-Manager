package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.DiscountType;
import br.com.movie.repository.DiscountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountTypeService {

    @Autowired
    private DiscountTypeRepository repository;

    private static final String DISCOUNT_TYPE_NOT_FOUND = "Discount type not found";

    public List<DiscountType> list() {
        return repository.findAll();
    }

    public DiscountType findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException(DISCOUNT_TYPE_NOT_FOUND));
    }

    public DiscountType findByDescription(String description) {
        Optional<DiscountType> optional = repository.findByDescriptionIgnoreCase(description);
        if (optional.isEmpty()) {
            throw new BadRequestException(DISCOUNT_TYPE_NOT_FOUND);
        }

        return optional.get();
    }

    public DiscountType save(DiscountType discountType) {
        Optional<DiscountType> optional = repository.findByDescriptionIgnoreCase(discountType.getDescription());

        if (optional.isPresent()) {
            throw new BadRequestException("There is already a discount type with this description");
        }

        return repository.save(discountType);
    }

    public DiscountType replace(DiscountType discountType) {
        if (repository.existsById(discountType.getId()) && isValidToReplace(discountType)) {
            return repository.save(discountType);
        } else {
            throw new BadRequestException(DISCOUNT_TYPE_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new BadRequestException(DISCOUNT_TYPE_NOT_FOUND);
        }
    }

    private boolean isValidToReplace(DiscountType discountType) {
        Optional<DiscountType> optional = repository.findByDescriptionIgnoreCase(discountType.getDescription());

        if (optional.isPresent() && !optional.get().getId().equals(discountType.getId())) {
            throw new BadRequestException("There is already a discount type with this description");
        }
        return true;
    }

}