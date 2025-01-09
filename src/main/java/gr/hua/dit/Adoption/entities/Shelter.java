package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shelter")
public class Shelter extends User{

    @ElementCollection
    @CollectionTable(name = "shelter_pets", joinColumns = @JoinColumn(name = "shelter_id"))
    @Column(name = "pets")
    private List<String> shelterPets;

    public Shelter() {}

    public Shelter(List<String> shelterPets) {this.shelterPets = shelterPets;}

    public List<String> getShelterPets() {return shelterPets;}
    public void setShelterPets(List<String> shelterPets) {this.shelterPets = shelterPets;}
}
