package SpringBt.service;

import java.util.List;
import java.util.Optional;
import SpringBt.model.Film;
import SpringBt.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findAll(String search) {
        if (!search.isEmpty()) {
            return filmRepository.findByTitleContaining(search);
        } else {
            return filmRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Film insert(Film film) {
        return filmRepository.save(film);
    }

    @Override
    @Transactional
    public Film update(Long id, Film filmDetails) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found for this id :: " + id));
        film.setTitle(filmDetails.getTitle());
        film.setDescription(filmDetails.getDescription());
        film.setYear(filmDetails.getYear());
        film.setReleaseDate(filmDetails.getReleaseDate());
        film.setDuration(filmDetails.getDuration());
        final Film updatedFilm = filmRepository.save(film);
        return updatedFilm;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found for this id :: " + id));
        filmRepository.delete(film);
    }
}
