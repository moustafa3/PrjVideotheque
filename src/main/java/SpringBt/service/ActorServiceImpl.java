package SpringBt.service;

import java.util.List;
import java.util.Optional;
import SpringBt.model.Actor;
import SpringBt.repository.ActorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    @Transactional
    public Optional<Actor> findById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    @Transactional
    public Actor insert(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    @Transactional
    public Actor update(Long id, Actor actorDetails) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found for this id :: " + id));
        actor.setName(actorDetails.getName());
        actor.setBio(actorDetails.getBio());
        return actorRepository.save(actor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Actor not found"));
        actor.getFilms().forEach(film -> film.getActors().remove(actor));
        actorRepository.delete(actor);
    }
}
