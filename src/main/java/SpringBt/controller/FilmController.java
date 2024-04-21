package SpringBt.controller;

import SpringBt.model.Film;
import SpringBt.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms(@RequestParam(value = "search", defaultValue = "") String search) {
        List<Film> films = filmService.findAll(search);
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Optional<Film> film = filmService.findById(id);
        return film.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody Film film) {
        Film savedFilm = filmService.insert(film);
        return ResponseEntity.ok().body(savedFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @Valid @RequestBody Film film) {
        Film updatedFilm = filmService.update(id, film);
        if (updatedFilm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
