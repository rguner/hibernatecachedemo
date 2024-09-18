package net.shahto.hibernatecache.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shahto.hibernatecache.model.Planet;
import net.shahto.hibernatecache.model.Star;
import net.shahto.hibernatecache.repositories.PlanetRepository;
import net.shahto.hibernatecache.repositories.StarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StarService {

    private final StarRepository starRepository;

    public Star createStar(Star star) {
        return starRepository.save(star);
    }

    public List<Star> getAllStars() {
        return starRepository.findAll();
    }

    public Optional<Star> getStarById(long id) {
        log.debug("1. query star # {} from database", id);
        starRepository.findById(id);
        log.debug("2. query star # {} from first level cache", id);
        starRepository.findById(id);
        log.debug("3. query star # {} from first level cache", id);
        starRepository.findById(id);
        log.debug("4. query star # {} from first level cache", id);
        return starRepository.findById(id);
    }

    public Optional<Star> getStarByIdForMultipleCall(long id) {
        log.debug("1. query star # {} from database", id);
        return starRepository.findById(id);
    }
}