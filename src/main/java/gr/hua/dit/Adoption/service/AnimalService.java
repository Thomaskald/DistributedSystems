package gr.hua.dit.Adoption.service;

import gr.hua.dit.Adoption.entities.Animal;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.repositories.AnimalRepository;
import gr.hua.dit.Adoption.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    public AnimalService(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Integer saveAnimal(Animal animal) {
        animal = animalRepository.save(animal);
        return animal.getId();
    }

    @Transactional
    public Integer updateAnimal(Animal animal) {
        /*Shelter shelter = (Shelter) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        animal.setShelter(shelter);*/
        animal = animalRepository.save(animal);
        return animal.getId();
    }

    public Object getAnimal(int animalId) {
        return animalRepository.findById(animalId).get();
    }

    @Transactional
    public Object getAnimals() {
        return animalRepository.findAll();
    }
}