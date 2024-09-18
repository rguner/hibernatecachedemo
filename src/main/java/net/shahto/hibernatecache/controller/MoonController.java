package net.shahto.hibernatecache.controller;

import lombok.AllArgsConstructor;
import net.shahto.hibernatecache.model.Moon;
import net.shahto.hibernatecache.services.MoonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/moons")
public class MoonController {

    private MoonService moonService;

    // http://localhost:8080/api/moons/1
    @GetMapping("{id}")
    public ResponseEntity<Moon> getMoonById(@PathVariable("id") Long moonId) {
        Optional<Moon> moonOptional = moonService.getMoonById(moonId);
        return moonOptional.map(moon -> new ResponseEntity<>(moon, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // http://localhost:8080/api/moons
    @GetMapping
    public ResponseEntity<List<Moon>> getAllMoons() {
        List<Moon> moons = moonService.getAllMoons();
        return new ResponseEntity<>(moons, HttpStatus.OK);
    }

}
