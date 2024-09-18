package net.shahto.hibernatecache.repositories;

import net.shahto.hibernatecache.model.Planet;
import net.shahto.hibernatecache.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
}
