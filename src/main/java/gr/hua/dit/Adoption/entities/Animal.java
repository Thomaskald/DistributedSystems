package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "species")
    private String species;

    @Column(name = "description")
    private String description;

    @Column(name = "animal_status")
    private String animalStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "shelter_id", referencedColumnName = "id")
    private User shelter;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "adopter_id", referencedColumnName = "id")
    private User adopter;

    public Animal() {}
    public Animal(String name, String gender, String age, String species, String description, Shelter shelter, Adopter adopter, String animalStatus) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.species = species;
        this.description = description;
        this.shelter = shelter;
        this.adopter = adopter;
        this.animalStatus = animalStatus;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getShelter() {return shelter;}

    public void setShelter(User shelter) {this.shelter = shelter;}

    public User getAdopter() {
        return adopter;
    }

    public void setAdopter(User adopter) {
        this.adopter = adopter;
    }

    public String getAnimalStatus() {return animalStatus;}

    public void setAnimalStatus(String animalStatus) {this.animalStatus = animalStatus;}

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
