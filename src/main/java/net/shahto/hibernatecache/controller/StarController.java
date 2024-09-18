package net.shahto.hibernatecache.controller;

import lombok.AllArgsConstructor;
import net.shahto.hibernatecache.model.Star;
import net.shahto.hibernatecache.services.StarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/stars")
public class StarController {

    private StarService starService;

    @PostMapping
    public ResponseEntity<Star> createStar(@RequestBody Star star) {
        Star savedStar = starService.createStar(star);
        return new ResponseEntity<>(savedStar, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/stars/1
    @GetMapping("{id}")
    public ResponseEntity<Star> getStarById(@PathVariable("id") Long starId) {
        Optional<Star> starOptional = starService.getStarById(starId);
        return starOptional.map(star -> new ResponseEntity<>(star, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getStarByIdFromMultipleServiceCall/{id}")
    public ResponseEntity<Star> getStarByIdFromMultipleServiceCall(@PathVariable("id") Long starId) {
        starService.getStarByIdForMultipleCall(starId);
        starService.getStarByIdForMultipleCall(starId);
        Optional<Star> starOptional = starService.getStarByIdForMultipleCall(starId);
        return starOptional.map(star -> new ResponseEntity<>(star, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // http://localhost:8080/api/stars
    @GetMapping
    public ResponseEntity<List<Star>> getAllStars() {
        List<Star> stars = starService.getAllStars();
        return new ResponseEntity<>(stars, HttpStatus.OK);
    }

}
