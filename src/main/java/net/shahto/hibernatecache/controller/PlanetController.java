package net.shahto.hibernatecache.controller;

import lombok.AllArgsConstructor;
import net.shahto.hibernatecache.model.Planet;
import net.shahto.hibernatecache.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/planets")
public class PlanetController {

    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet Planet) {
        Planet savedPlanet = planetService.createPlanet(Planet);
        return new ResponseEntity<>(savedPlanet, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/olanets/1
    @GetMapping("{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") Long planetId) {
        Optional<Planet> planetOptional = planetService.getPlanetById(planetId);
        return planetOptional.map(planet -> new ResponseEntity<>(planet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // http://localhost:8080/api/planets
    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.getAllPlanets();
        return new ResponseEntity<>(planets, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Planet> updatePlanet(@PathVariable("id") Long planetId,
                                                 @RequestBody Planet planet){
        planet.setId(planetId);
        Planet updatedPlanet = planetService.updatePlanet(planet);
        return new ResponseEntity<>(updatedPlanet, HttpStatus.OK);
    }

}
