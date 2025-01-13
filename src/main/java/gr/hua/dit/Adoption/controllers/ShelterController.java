package gr.hua.dit.Adoption.controllers;

import gr.hua.dit.Adoption.entities.Animal;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.entities.Vet;
import gr.hua.dit.Adoption.repositories.AnimalRepository;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import gr.hua.dit.Adoption.repositories.UserRepository;
import gr.hua.dit.Adoption.service.AnimalService;
import gr.hua.dit.Adoption.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShelterController {
    private UserService userService;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public ShelterController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("register/shelter")
    public String registerShelter(Model model) {
        Shelter shelter = new Shelter();
        model.addAttribute("shelter", shelter);
        return "register/shelter";
    }

    @PostMapping("/saveShelter")
    public String saveShelter(@ModelAttribute Shelter shelter, Model model){
        System.out.println("Roles: "+shelter.getRoles());
        Integer id = userService.saveUser(shelter);
        String message = "Shelter '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "index";
    }

    @PostMapping("/user/{shelter_id}")
    public String saveShelter(@PathVariable Long shelter_id, @ModelAttribute("Shelter") Shelter shelter, Model model) {
        Shelter the_shelter = (Shelter) userService.getUser(shelter_id);
        the_shelter.setUsername(shelter.getUsername());
        the_shelter.setEmail(shelter.getEmail());
        the_shelter.setPassword(shelter.getPassword());
        the_shelter.setPhoneNumber(shelter.getPhoneNumber());
        the_shelter.setAddress(shelter.getAddress());
        the_shelter.setRoles(shelter.getRoles());
        //the_shelter.setLicenseNumber(shelter.getLicenseNumber());
        userService.updateUser(the_shelter);
        System.out.println(shelter);
        model.addAttribute("shelters", userService.getUsers());
        return "auth/users";
    }



}