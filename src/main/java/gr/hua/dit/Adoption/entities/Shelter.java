package gr.hua.dit.Adoption.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shelterName")
    private String shelterName;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private long phoneNumber;

    @Column(name = "email")
    private String email;


}
