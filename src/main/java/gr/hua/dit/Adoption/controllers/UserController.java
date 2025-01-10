package gr.hua.dit.Adoption.controllers;

import gr.hua.dit.Adoption.entities.Role;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import gr.hua.dit.Adoption.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    private RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/register";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        System.out.println("Roles: "+user.getRoles());
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";
    }

    @GetMapping("/user/{user_id}")
    public String showUser(@PathVariable Long user_id, Model model){
        model.addAttribute("user", userService.getUser(user_id));
        return "auth/user";
    }

    @PostMapping("/user/{user_id}")
    public String saveUser(@PathVariable Long user_id, @ModelAttribute("user") User user, Model model) {
        User the_user = (User) userService.getUser(user_id);
        the_user.setUsername(user.getUsername());
        the_user.setEmail(user.getEmail());
        the_user.setPassword(user.getPassword());
        the_user.setPhoneNumber(user.getPhoneNumber());
        the_user.setAddress(user.getAddress());
        the_user.setRoles(user.getRoles());
        userService.updateUser(the_user);
        System.out.println(user);
        model.addAttribute("users", userService.getUsers());
        return "auth/users";
    }

    @GetMapping("/user/role/delete/{user_id}/{role_id}")
    public String deleteRoleFromUser(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Role role = roleRepository.findById(role_id).get();
        user.getRoles().remove(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";

    }

    @GetMapping("/user/role/add/{user_id}/{role_id}")
    public String addRoleToUser(@PathVariable Long user_id, @PathVariable Integer role_id, Model model){
        User user = (User) userService.getUser(user_id);
        Role role = roleRepository.findById(role_id).get();
        user.getRoles().add(role);
        System.out.println("Roles: "+user.getRoles());
        userService.updateUser(user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "auth/users";

    }

}
