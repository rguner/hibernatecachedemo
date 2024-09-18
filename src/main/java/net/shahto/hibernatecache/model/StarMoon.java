package net.shahto.hibernatecache.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "star_moons")
public class StarMoon {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "star_id", nullable = false)
    @JsonIgnore //infinite recursion when Jacson serializing entity into JSON
    private Star star;

    private String name;

    private int diameter;

    @Override
    public String toString() {
        return "StarMoon {" +
                "id=" + id +
                ", star=" + star.getName() +
                ", name='" + name + '\'' +
                ", diameter=" + diameter +
                '}';
    }
}