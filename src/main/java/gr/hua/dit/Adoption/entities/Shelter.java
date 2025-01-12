package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "shelter")
public class Shelter extends User{

    @ElementCollection
    @CollectionTable(name = "shelter_pets", joinColumns = @JoinColumn(name = "shelter_id"))
    @Column(name = "pets")
    private List<String> shelterPets;

    @OneToMany(mappedBy = "shelter")
    private List<Animal> animals;

    public Shelter(List<Animal> animals) {
        this.animals = animals;
    }

    public Shelter(List<String> shelterPets, List<Animal> animals) {this.shelterPets = shelterPets;
        this.animals = animals;
    }

    public Shelter() {

    }

    public List<String> getShelterPets() {return shelterPets;}
    public void setShelterPets(List<String> shelterPets) {this.shelterPets = shelterPets;}

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
