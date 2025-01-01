package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//
//    private long licenseNumber;
//
//    private List<String> checkedPets;
//
//    public Vet() {
//
//    }
//
//    public Vet(long licenseNumber, List<String> checkedPets) {
//        this.licenseNumber = licenseNumber;
//        this.checkedPets = checkedPets;
//    }
//
//    public long getLicenseNumber() {return licenseNumber;}
//    public void setLicenseNumber(long licenseNumber) {this.licenseNumber = licenseNumber;}

/*    public List<String> getCheckedPets() {return checkedPets;}
    public void setCheckedPets(List<String> checkedPets) {this.checkedPets = checkedPets;}*/

}
