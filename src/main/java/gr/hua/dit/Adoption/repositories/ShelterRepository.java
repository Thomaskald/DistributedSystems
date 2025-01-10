package gr.hua.dit.Adoption.repositories;

import gr.hua.dit.Adoption.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<User, Long> {
}