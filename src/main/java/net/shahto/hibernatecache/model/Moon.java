package net.shahto.hibernatecache.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moons")
public class Moon {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    @JsonIgnore //infinite recursion when Jacson serializing entity into JSON
    private Planet planet;

    private String name;

    private int diameter;

    @Override
    public String toString() {
        return "Moon{" +
                "id=" + id +
                ", planet=" + //planet.getName() +
                ", name='" + name + '\'' +
                ", diameter=" + diameter +
                '}';
    }
}