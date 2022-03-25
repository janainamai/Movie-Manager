package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.*;
import br.com.movie.model.dto.BookArmchairInput;
import br.com.movie.model.dto.SessionSaveInput;
import br.com.movie.model.dto.UnbookArmchairInput;
import br.com.movie.repository.ArmchairRepository;
import br.com.movie.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private SessionRepository SessionRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ArmchairRepository armchairRepository;
    @Autowired
    private RoomService roomService;

    @Transactional
    public List<Session> save(SessionSaveInput input) {
        List<Session> session = generateSessions(input);
        generateArmchairs(session);

        return session;
    }

    private List<Session> generateSessions(SessionSaveInput input) {
        List<Session> sessions = new ArrayList<>();
        Room room = roomService.findById(input.getRoomId());
        Movie movie = movieService.findById(input.getMovieId());

        input.getDays().forEach(day -> {
            input.getHours().forEach(hour -> {

                Session session = Session.builder()
                        .movie(movie)
                        .dateTime(LocalDateTime.of(day.toLocalDate(), hour.toLocalTime()))
                        .room(room)
                        .build();

                sessions.add(session);

            });
        });

        return SessionRepository.saveAll(sessions);
    }

    private void generateArmchairs(List<Session> sessions) {
        List<Armchair> sessionArmchairs = new ArrayList<>();

        sessions.forEach(session -> {
            List<ArmchairModel> armchairsMold = roomService.getArmchairsByRoomId(session.getRoom().getId());

            List<Armchair> armchairs = armchairsMold.stream()
                    .map(chair -> Armchair.builder()
                            .sessionId(session.getId())
                            .letterNumber(chair.getLetter() + chair.getNumber())
                            .available(true)
                            .build())
                    .collect(Collectors.toList());

            sessionArmchairs.addAll(armchairs);
        });

        armchairRepository.saveAll(sessionArmchairs);
    }

    @Transactional(readOnly = true)
    public List<Armchair> getArmchairsBySessionId(Integer sessionId) {
        return armchairRepository.findBySessionIdGroupByLetterNumber(sessionId);
    }

    @Transactional
    public List<Armchair> bookArmchairs(BookArmchairInput input) {
        List<Armchair> armchairs = new ArrayList<>();

        input.getArmchairsId().forEach(id -> {
            Armchair armchair = armchairRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("No room was found with id " + id));

            armchair.setAvailable(false);

            armchairs.add(armchair);
        });

        armchairRepository.saveAll(armchairs);
        return armchairRepository.findBySessionIdGroupByLetterNumber(input.getSessionId());
    }

    @Transactional
    public List<Armchair> unbookArmchairs(UnbookArmchairInput input) {
        List<Armchair> armchairs = new ArrayList<>();

        input.getArmchairsId().forEach(id -> {
            Armchair armchair = armchairRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("No room was found with id " + id));

            armchair.setAvailable(true);

            armchairs.add(armchair);
        });

        armchairRepository.saveAll(armchairs);
        return armchairRepository.findBySessionIdGroupByLetterNumber(input.getSessionId());
    }

}
