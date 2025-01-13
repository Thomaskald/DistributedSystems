package gr.hua.dit.Adoption.repositories;

import gr.hua.dit.Adoption.entities.Role;
import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.StringJoiner;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.username = :username AND u.roles = 'SHELTER'")
//    User findShelterByUsername(String username);
    User findByUsername(String username);
    User findUserByEmail(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

//    User findUserByRole(Role role);
    User findByStatus(String pending);
}
