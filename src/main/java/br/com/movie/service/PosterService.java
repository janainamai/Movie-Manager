package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.*;
import br.com.movie.model.dto.BookArmchairInput;
import br.com.movie.model.dto.PosterSaveInput;
import br.com.movie.model.dto.UnbookArmchairInput;
import br.com.movie.repository.PosterArmchairRepository;
import br.com.movie.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosterService {

    @Autowired
    private PosterRepository posterRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private PosterArmchairRepository posterArmchairRepository;
    @Autowired
    private RoomService roomService;

    @Transactional
    public List<Poster> save(PosterSaveInput input) {
        List<Poster> posters = generatePosters(input);
        generateArmchairs(posters);

        return posters;
    }

    private List<Poster> generatePosters(PosterSaveInput input) {
        List<Poster> posters = new ArrayList<>();
        Room room = roomService.findById(input.getRoomId());
        Movie movie = movieService.findById(input.getMovieId());

        input.getDays().forEach(day -> {
            input.getHours().forEach(hour -> {

                Poster poster = Poster.builder()
                        .movie(movie)
                        .dateTime(LocalDateTime.of(day.toLocalDate(), hour.toLocalTime()))
                        .room(room)
                        .build();

                posters.add(poster);

            });
        });

        return posterRepository.saveAll(posters);
    }

    private void generateArmchairs(List<Poster> posters) {
        List<PosterArmchair> posterArmchairs = new ArrayList<>();

        posters.forEach(poster -> {
            List<Armchair> armchairsMold = roomService.getArmchairsByRoomId(poster.getRoom().getId());

            List<PosterArmchair> armchairs = armchairsMold.stream()
                    .map(chair -> PosterArmchair.builder()
                            .posterId(poster.getId())
                            .letterNumber(chair.getLetter() + chair.getNumber())
                            .available(true)
                            .build())
                    .collect(Collectors.toList());

            posterArmchairs.addAll(armchairs);
        });

        posterArmchairRepository.saveAll(posterArmchairs);
    }

    @Transactional(readOnly = true)
    public List<PosterArmchair> getArmchairsByPosterId(Integer posterId) {
        return posterArmchairRepository.findByPosterIdGroupByLetterNumber(posterId);
    }

    @Transactional
    public List<PosterArmchair> bookArmchairs(BookArmchairInput input) {
        List<PosterArmchair> armchairs = new ArrayList<>();

        input.getArmchairsId().forEach(id -> {
            PosterArmchair armchair = posterArmchairRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("No room was found with id " + id));

            armchair.setAvailable(false);

            armchairs.add(armchair);
        });

        posterArmchairRepository.saveAll(armchairs);
        return posterArmchairRepository.findByPosterIdGroupByLetterNumber(input.getPosterId());
    }

    @Transactional
    public List<PosterArmchair> unbookArmchairs(UnbookArmchairInput input) {
        List<PosterArmchair> armchairs = new ArrayList<>();

        input.getArmchairsId().forEach(id -> {
            PosterArmchair armchair = posterArmchairRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("No room was found with id " + id));

            armchair.setAvailable(true);

            armchairs.add(armchair);
        });

        posterArmchairRepository.saveAll(armchairs);
        return posterArmchairRepository.findByPosterIdGroupByLetterNumber(input.getPosterId());
    }

}
