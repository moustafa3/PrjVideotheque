package SpringBt.service;

import SpringBt.model.Film;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findById(Long id);
    List<Film> findAll(String search);
    Film insert(Film film);
    Film update(Long id, Film film);
    void delete(Long id);
}
