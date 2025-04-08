package org.logic;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numClient;

    @Column(nullable = false)
    private String petName;

    private String breed;
    private String petColor;
    private String allergic;
    private String specialAttention;
    private String observations;

    @ManyToOne
    @JoinColumn(name= "idOwner", nullable = false)
    private Owner owner;

    public Pet(){}
    public Pet(int numClient, String petName, String breed, String petColor, String allergic, String specialAttention, String observations, Owner owner) {
        this.numClient = numClient;
        this.petName = petName;
        this.breed = breed;
        this.petColor = petColor;
        this.allergic = allergic;
        this.specialAttention = specialAttention;
        this.observations = observations;
        this.owner = owner;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getAllergic() {
        return allergic;
    }

    public void setAllergic(String allergic) {
        this.allergic = allergic;
    }

    public String getSpecialAttention() {
        return specialAttention;
    }

    public void setSpecialAttention(String specialAttention) {
        this.specialAttention = specialAttention;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {return petName + " - " + breed + " - " + petColor;}
}
