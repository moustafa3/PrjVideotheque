package SpringBt.service;

import SpringBt.model.Actor;
import java.util.List;
import java.util.Optional;

public interface ActorService {
    Optional<Actor> findById(Long id);
    List<Actor> findAll();
    Actor insert(Actor actor);
    Actor update(Long id, Actor actor);
    void delete(Long id);
}
