package gr.hua.dit.Adoption.repositories;

import gr.hua.dit.Adoption.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByShelterId(Long shelterId);
    Animal findAnimalById(int id);
    List<Animal> findAnimalByAnimalStatus(String animalStatus);
    List<Animal> findAnimalByAnimalHealthStatus(String animalHealthStatus);

    List<Animal> findAnimalByAnimalAdoptionStatus(String notAdopted);
}
