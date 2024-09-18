package net.shahto.hibernatecache.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shahto.hibernatecache.model.Moon;
import net.shahto.hibernatecache.repositories.MoonRepository;
import net.shahto.hibernatecache.repositories.MoonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoonService {

    private final MoonRepository moonRepository;

    public Moon createMoon(Moon Moon) {
        return moonRepository.save(Moon);
    }

    public List<Moon> getAllMoons() {
        return moonRepository.findAll();
    }

    // second level cach Planet sorgusunda çalışmaz. getMoonById sorgusunda çalışır.
    // Planet sorgusu ile : Hibernate: select m1_0.planet_id,m1_0.id,m1_0.diameter,m1_0.name from moons m1_0 where m1_0.planet_id=?
    // bu metod ile Hibernate: select m1_0.id,m1_0.diameter,m1_0.name,p1_0.id,p1_0.diameter,p1_0.distance,p1_0.mass,p1_0.name,p1_0.type from moons m1_0 join planets p1_0 on p1_0.id=m1_0.planet_id where m1_0.id=?
    public Optional<Moon> getMoonById(long id) {
        log.debug("__________________________________________________________________________________________________");
        log.debug("1. query Moon # {} from database or second cache", id);
        moonRepository.findById(id);
        log.debug("2. query Moon # {} from first level cache", id);
        moonRepository.findById(id);
        log.debug("3. query Moon # {} from first level cache", id);
        moonRepository.findById(id);
        log.debug("4. query Moon # {} from first level cache", id);
        return moonRepository.findById(id);
    }
}