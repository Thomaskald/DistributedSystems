package gr.hua.dit.Adoption.service;

import gr.hua.dit.Adoption.entities.Role;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import gr.hua.dit.Adoption.entities.Vet;
import gr.hua.dit.Adoption.exceptions.UserNotEnabledException;
import gr.hua.dit.Adoption.repositories.RoleRepository;
import gr.hua.dit.Adoption.repositories.ShelterRepository;
import gr.hua.dit.Adoption.repositories.UserRepository;
import gr.hua.dit.Adoption.repositories.VetRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final VetRepository vetRepository;
    private final ShelterRepository shelterRepository;
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, VetRepository vetRepository, ShelterRepository shelterRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.vetRepository = vetRepository;
        this.shelterRepository = shelterRepository;
    }

    @Transactional
    public Integer saveUser(User user) {
        String passwd= user.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        user.setPassword(encodedPassword);
        Role role;
        if (user instanceof Vet) {
            role = roleRepository.findByName("ROLE_VET")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setStatus("ENABLED");
        } else if(user instanceof Shelter) {
            role = roleRepository.findByName("ROLE_SHELTER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setStatus("PENDING");
        }else{
            role = roleRepository.findByName("ROLE_ADOPTER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setStatus("ENABLED");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user = userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public Integer updateUser(User user) {
        user = userRepository.save(user);
        return user.getId();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = Optional.ofNullable(userRepository.findByUsername(username));

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " +username +" not found !");
        else {
            User user = opt.get();
            if (!"ENABLED".equals(user.getStatus())){
                throw new UserNotEnabledException("User with username: " +username +" is not enabled !");
            }
                return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toSet())
            );
        }
    }

    @Transactional
    public Object getUsers() {
        return userRepository.findAll();
    }


    public Object getPendingShelters() {
        return userRepository.findByStatus("PENDING");
    }

    public Object getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Transactional
    public void updateOrInsertRole(Role role) {
        roleRepository.updateOrInsert(role);
    }
}