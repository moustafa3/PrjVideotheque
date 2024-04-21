package SpringBt.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Bio can't be empty")
    @Size(max = 1000, message = "Bio cannot be longer than 1000 characters")
    @Column(length = 1000)
    private String bio;


    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    @JsonIgnore
    private List<Film> films = new ArrayList<>();


    public Actor() {
        this.films = new ArrayList<>();
    }

    public Actor(String name, String bio) {
        this.name = name;
        this.bio = bio;
        this.films = new ArrayList<>();
    }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        if (film != null) {
            if (this.films == null) {
                this.films = new ArrayList<>();
            }
            this.films.add(film);
            if (film.getActors() == null) {
                film.setActors(new ArrayList<>());
            }
            film.getActors().add(this);
        }
    }
    public void removeFilm(Film film) {
        if (film != null && this.films != null && film.getActors() != null) {
            this.films.remove(film);
            film.getActors().remove(this);
        }
    }
}
