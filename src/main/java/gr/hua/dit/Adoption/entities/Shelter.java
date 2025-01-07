package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shelter")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shelterName")
    private String shelterName;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private long phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "shelter_admin_shelter",
            joinColumns = @JoinColumn(name = "shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "shelter_admin_id")
    )
    private Set<ShelterAdmin> shelterAdmins;

    @OneToMany(mappedBy = "shelter", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Animal> animals;

    public Shelter() {}
    public Shelter(String shelterName, String address, long phoneNumber, String email) {
        this.shelterName = shelterName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getShelterName() {return shelterName;}
    public void setShelterName(String shelterName) {this.shelterName = shelterName;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public long getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(long phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Shelter{" +
                "name='" + shelterName + '\'' +
                "address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
