package net.shahto.hibernatecache.services;

import lombok.RequiredArgsConstructor;
import net.shahto.hibernatecache.model.Planet;
import net.shahto.hibernatecache.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;

    public Planet createPlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Optional<Planet> getPlanetById(long id) {
        return planetRepository.findById(id);
    }
}