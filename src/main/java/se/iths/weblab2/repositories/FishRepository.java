package se.iths.weblab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.weblab2.entities.Fish;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {
    List<Fish> findAllByName(String name);
}
