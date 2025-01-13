package gr.hua.dit.Adoption.service;

import gr.hua.dit.Adoption.Utils.LoggedInUserUtils;
import gr.hua.dit.Adoption.entities.Animal;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.repositories.AnimalRepository;
import gr.hua.dit.Adoption.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;
    private UserRepository userRepository;

    public AnimalService(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Integer Adoption(Animal animal) {

        String username = LoggedInUserUtils.getLoggedInUsername();
        User user = userRepository.findUserByEmail(username);
        animal.setAdopter(user);

        animalRepository.save(animal);
        return animal.getId();
    }

    @Transactional
    public Integer saveAnimal(Animal animal) {
        String username = LoggedInUserUtils.getLoggedInUsername();
        User user = userRepository.findUserByEmail(username);
        animal.setShelter(user);

        animal = animalRepository.save(animal);
        return animal.getId();
    }

    @Transactional
    public Integer updateAnimal(Animal animal) {

        String username = LoggedInUserUtils.getLoggedInUsername();
        User user = userRepository.findUserByEmail(username);
        animal.setShelter(user);

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