package net.shahto.hibernatecache.repositories;

import net.shahto.hibernatecache.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> findByNameIs(String name);
}
