package gr.hua.dit.Adoption.controllers;

import gr.hua.dit.Adoption.Utils.LoggedInUserUtils;
import gr.hua.dit.Adoption.entities.Animal;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.repositories.UserRepository;
import gr.hua.dit.Adoption.service.AnimalService;
import gr.hua.dit.Adoption.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AnimalService animalService;

    public AdminController(UserRepository userRepository, UserService userService, AnimalService animalService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.animalService = animalService;
    }

    @GetMapping("/auth/pending-shelters")
    public String pendingShelters(Model model) {
        model.addAttribute("shelters", userService.getPendingShelters());
        return "auth/pending-shelters";
    }

    @GetMapping("/admin/shelters/approve/{shelter_id}")
    public String approve(@PathVariable long shelter_id, Model model) {
        User user = (User) userService.getUser(shelter_id);
        user.setStatus("ENABLED");
        userService.updateUser(user);
        return "index";
    }

    @GetMapping("/auth/pending-animals")
    public String pendingAnimals(Model model) {
        model.addAttribute("animals", animalService.getPendingAnimals());
        return "auth/pending-animals";
    }

    @GetMapping("/admin/animals/approve/{animal_id}")
    public String approveAnimal(@PathVariable int animal_id, Model model) {
        Animal animal = (Animal) animalService.getAnimal(animal_id);
        animal.setAnimalStatus("ENABLED");
        animalService.updateAnimal(animal);
        return "index";
    }
}
