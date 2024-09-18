package net.shahto.hibernatecache.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/cache")
@Slf4j
public class CacheController {

    private final CacheManager cacheManager;

    @PersistenceContext
    private EntityManager entityManager;

    // http://localhost:8080/api/cache
    @GetMapping("/clearCaches")
    public ResponseEntity<Void> clearCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            log.debug("cacheName = {}",cacheName);
            log.debug("cache = {}", cacheManager.getCache(cacheName));
            cacheManager.getCache(cacheName).clear();
        });

        // second level cache evict
        SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getCache().evictAllRegions();
        log.debug("Second Level Cache evicted successfully");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
