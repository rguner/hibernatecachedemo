package net.shahto.hibernatecache.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shahto.hibernatecache.model.Planet;
import net.shahto.hibernatecache.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

    private final PlanetRepository planetRepository;

    public Planet createPlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Optional<Planet> getPlanetById(long id) {
        log.debug("__________________________________________________________________________________________________");
        log.debug("1. query planet # {} from database or second cache", id);
        planetRepository.findById(id);
        log.debug("2. query planet # {} from first level cache", id);
        planetRepository.findById(id);
        log.debug("3. query planet # {} from first level cache", id);
        planetRepository.findById(id);
        log.debug("4. query planet # {} from first level cache", id);
        return planetRepository.findById(id);
    }

    public Planet updatePlanet(Planet planet) {
        Optional<Planet> existingPlanetOptional = planetRepository.findById(planet.getId());
        if (existingPlanetOptional.isPresent()) {
            Planet existingPlanet = existingPlanetOptional.get();
            existingPlanet.setName(existingPlanet.getName() + " updated");
            return planetRepository.save(existingPlanet);
        } else {
            throw new RuntimeException("Planet Not Found with Id " + planet.getId());
        }
    }

    public Optional<Planet> getPlanetByName(String name) {
        return planetRepository.findByNameIs(name);
    }

    @Cacheable(value = "springCache", key = "#id", unless = "#result == null")
    public Planet getSpringCachedPlanetById(Long id) {

       return planetRepository.findById(id).orElseThrow(() -> new RuntimeException("Planet Not Found with Id " + id));
    }
}