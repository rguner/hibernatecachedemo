package net.shahto.hibernatecache.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "stars")
// @Cache(region = "starCache", usage = CacheConcurrencyStrategy.READ_WRITE)
// Planet is in second level cache, Star not in cache
public class Star {
    @Id
    private long id;

    private String name;

    private String type;

    private float mass;

    private int diameter;

    private float distance;

    @OneToMany(mappedBy="star", cascade=CascadeType.ALL)
    private Set<StarMoon> starMoons;
}