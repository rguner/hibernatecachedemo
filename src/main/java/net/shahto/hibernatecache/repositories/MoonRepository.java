package net.shahto.hibernatecache.repositories;

import net.shahto.hibernatecache.model.Moon;
import net.shahto.hibernatecache.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoonRepository extends JpaRepository<Moon, Long> {

    Optional<Moon> findByNameIs(String name);
}
