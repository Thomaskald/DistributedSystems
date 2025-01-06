package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
//@Table(name = "adopter")
public class Adopter extends User{



    @ElementCollection
    @CollectionTable(name = "adoption_applications", joinColumns = @JoinColumn(name = "adopter_id"))
    @Column(name = "application")
    private List<String> adoptionApplicationList;

    public List<String> getAdoptionApplicationList() {
        return adoptionApplicationList;
    }

    public void setAdoptionApplicationList(List<String> adoptionApplicationList) {
        this.adoptionApplicationList = adoptionApplicationList;
    }

    public Adopter() {}

    public Adopter(String username, String password, String email, long phoneNumber, String address, List<String> adoptionApplicationList) {
        super(username, password, email, phoneNumber, address);
        this.adoptionApplicationList = adoptionApplicationList;
    }
}
