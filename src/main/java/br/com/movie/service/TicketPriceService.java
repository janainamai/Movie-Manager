package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.TicketPrice;
import br.com.movie.repository.TicketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketPriceService {

    @Autowired
    private TicketPriceRepository ticketPriceRepository;

    public List<TicketPrice> list() {
        return ticketPriceRepository.findAll();
    }

    public Double getCurrentPrice() {
        Optional<TicketPrice> currentTicketPrice = getCurrentTicketPrice();

        if (currentTicketPrice.isPresent()) {
            return currentTicketPrice.get().getPrice();
        } else {
            throw new BadRequestException("There is no registered price yet");
        }
    }

    /**
     * Inserts an end date into the current price (if exists) and saves a new price with today's start date.
     */
    @Transactional
    public TicketPrice saveNewTicketPrice(TicketPrice ticketPrice) {
        inactivateCurrentTicketPrice();
        return save(ticketPrice);
    }

    private TicketPrice save(TicketPrice ticketPrice) {
        ticketPrice.setCreated(LocalDate.now());
        return ticketPriceRepository.save(ticketPrice);
    }

    private void inactivateCurrentTicketPrice() {
        if (getCurrentTicketPrice().isPresent()) {
            getCurrentTicketPrice().get().setFinished(LocalDate.now());
        }
    }

    private Optional<TicketPrice> getCurrentTicketPrice() {
        List<TicketPrice> list = ticketPriceRepository
                .findAll()
                .stream()
                .filter(TicketPriceService::activeTicketPrice)
                .collect(Collectors.toList());

        if(list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0));
        }
    }

    private static boolean activeTicketPrice(TicketPrice price) {
        return price.getFinished() == null;
    }

}