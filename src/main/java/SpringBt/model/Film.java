package SpringBt.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @NotNull(message = "Release year is required")
    @Column(name = "release_year")
    private Integer release_year;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    @Column(length = 1000)
    private String description;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;

    @NotNull(message = "Duration is required")
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "Film_Actor",
            joinColumns = {@JoinColumn(name = "filmId")},
            inverseJoinColumns = {@JoinColumn(name = "actorId")}
    )
    @JsonBackReference
    private List<Actor> actors = new ArrayList<>();


    public Film() {
    }

    public Film(String title, Integer release_year, String description, Date release_date, Integer duration) {
        this.title = title;
        this.release_year = release_year;
        this.description = description;
        this.release_date = release_date;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return release_year;
    }

    public void setYear(Integer releaseYear) {
        this.release_year = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(Date releaseDate) {
        this.release_date = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor) {
        if (actor != null) {
            if (this.actors == null) {
                this.actors = new ArrayList<>();
            }
            this.actors.add(actor);

            if (actor.getFilms() == null) {
                actor.setFilms(new ArrayList<>());
            }
            actor.getFilms().add(this);
        }
    }

    public void removeActor(Actor actor) {
        if (actor != null && this.actors != null && actor.getFilms() != null) {
            this.actors.remove(actor);
            actor.getFilms().remove(this);
        }
    }
}
