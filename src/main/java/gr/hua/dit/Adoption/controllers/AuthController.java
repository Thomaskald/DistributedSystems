package gr.hua.dit.Adoption.controllers;

import gr.hua.dit.Adoption.entities.Role;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    RoleRepository roleRepository;

    public AuthController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void setup() {
        Role role_adopter = new Role("ROLE_ADOPTER");
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_vet = new Role("ROLE_VET");
        Role role_shelter_admin = new Role("ROLE_SHELTER");

        roleRepository.updateOrInsert(role_adopter);
        roleRepository.updateOrInsert(role_admin);
        roleRepository.updateOrInsert(role_vet);
        roleRepository.updateOrInsert(role_shelter_admin);
    }

    @GetMapping("/login")
    public String login(User user) {
//        User signedInUser = new User();
//        signedInUser.setId(user.getId());
        return "auth/login";
    }
}
