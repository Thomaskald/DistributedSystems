package gr.hua.dit.Adoption.controllers;


import gr.hua.dit.Adoption.entities.Animal;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import gr.hua.dit.Adoption.service.AnimalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService, RoleRepository roleRepository) {
        this.animalService = animalService;
    }

    @GetMapping("animal/add-animal")
    public String addAnimal(Model model) {
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "animal/add-animal";
    }

    @PostMapping("/saveAnimal")
    public String saveAnimal(@ModelAttribute Animal animal, Model model) {
        Integer id = animalService.saveAnimal(animal);
        String message = "Animal '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "index";
    }

    @PostMapping("/animal/{animal_id}")
    public String saveAnimal(@PathVariable int animal_id, @ModelAttribute Animal animal, Model model) {
        Animal the_animal = (Animal) animalService.getAnimal(animal_id);
        the_animal.setName(animal.getName());
        the_animal.setGender(animal.getGender());
        the_animal.setAge(animal.getAge());
        the_animal.setSpecies(animal.getSpecies());
        the_animal.setDescription(animal.getDescription());

        Shelter shelter = (Shelter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        the_animal.setShelter(shelter);


        animalService.updateAnimal(the_animal);
        model.addAttribute("animal", animalService.getAnimals());
        return "animal/animals";
    }
}
