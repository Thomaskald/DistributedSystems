package gr.hua.dit.Adoption.controllers;

import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.entities.Vet;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import gr.hua.dit.Adoption.repositories.UserRepository;
import gr.hua.dit.Adoption.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VetController {
    private UserService userService;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public VetController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("register/vet")
    public String registerVet(Model model) {
        Vet vet = new Vet();
        model.addAttribute("vet", vet);
        return "register/vet";
    }

    @PostMapping("/saveVet")
    public String saveVet(@ModelAttribute Vet vet, Model model){
        System.out.println("Roles: "+vet.getRoles());
        Integer id = userService.saveUser(vet);
        String message = "Vet '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "index";
    }

    @PostMapping("/user/{vet_id}")
    public String saveVet(@PathVariable Long vet_id, @ModelAttribute("Vet") Vet vet, Model model) {
        Vet the_vet = (Vet) userService.getUser(vet_id);
        the_vet.setUsername(vet.getUsername());
        the_vet.setEmail(vet.getEmail());
        the_vet.setPassword(vet.getPassword());
        the_vet.setPhoneNumber(vet.getPhoneNumber());
        the_vet.setAddress(vet.getAddress());
        the_vet.setRoles(vet.getRoles());
        the_vet.setLicenseNumber(vet.getLicenseNumber());
        userService.updateUser(the_vet);
        System.out.println(vet);
        model.addAttribute("vets", userService.getUsers());
        return "auth/users";
    }
}
