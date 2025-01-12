package gr.hua.dit.Adoption.repositories;

import gr.hua.dit.Adoption.entities.Shelter;
import gr.hua.dit.Adoption.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShelterRepository extends JpaRepository<User, Long> {
}