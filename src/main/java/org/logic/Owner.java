package org.logic;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idOwner;

    private String ownerName;
    private String ownerPhone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    public Owner(){}

    public Owner(int idOwner, String ownerName, String ownerPhone) {
        this.idOwner = idOwner;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
