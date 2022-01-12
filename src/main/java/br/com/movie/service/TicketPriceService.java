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

    public Double getCurrentPrice() {
        if (getCurrentTicketPrice().isPresent()) {
            return getCurrentTicketPrice().get().getPrice();
        } else {
            throw new BadRequestException("There is no registered price yet");
        }
    }

    /**
     * Inserts an end date into the current price (if exists) and saves a new price with today's start date.
     *
     * @param ticketPrice contains value of new price to be saved
     * @return saved price
     */
    @Transactional
    public TicketPrice saveNewTicketPrice(TicketPrice ticketPrice) {
        inactivateCurrentTicketPrice();

        return save(ticketPrice);
    }

    /**
     * Saves a new price with today's start date.
     *
     * @param ticketPrice contains value of new price to be saved
     * @return saved price
     */
    private TicketPrice save(TicketPrice ticketPrice) {
        ticketPrice.setCreated(LocalDate.now());
        return ticketPriceRepository.save(ticketPrice);
    }

    private void inactivateCurrentTicketPrice() {
        if (getCurrentTicketPrice().isPresent()) {
            getCurrentTicketPrice().get().setFinished(LocalDate.now());
        }
    }

    /**
     * Returns the price that has no end date.
     *
     * @return Optional of TicketPrice
     */
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