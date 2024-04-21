package SpringBt.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints=@UniqueConstraint(name = "us_name", columnNames={"name"})
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)

    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Film> films;

    public Category() {
        super();
        this.films = new ArrayList<>();
    }

    public Category(String name) {
        this.name = name;
        this.films = new ArrayList<>();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        if (this.films == null) {
            this.films = new ArrayList<>();
        }
        this.films.add(film);
        film.setCategory(this);
    }
    public void removeFilm(Film film) {
        if (this.films != null) {
            this.films.remove(film);
        }
        if (film != null) {
            film.setCategory(null);
        }
    }
}
