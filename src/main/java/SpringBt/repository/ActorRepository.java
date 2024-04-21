package SpringBt.repository;

import SpringBt.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContainingIgnoreCase(String name);
}
