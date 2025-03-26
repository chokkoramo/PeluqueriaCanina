package org.logic;

import org.persistence.PersistenceController;

import javax.swing.*;

public class Controller {
    PersistenceController controlPersis = new PersistenceController();

    public void save(String petName, String petBreed, String petColor, String petObservations, String ownerName, String ownerPhone, String allergic, String specialAttention) {
        Owner owner = new Owner();
        owner.setOwnerName(ownerName);
        owner.setOwnerPhone(ownerPhone);

        Pet pet = new Pet();
        pet.setPetName(petName);
        pet.setPetColor(petColor);
        pet.setBreed(petBreed);
        pet.setObservations(petObservations);
        pet.setAllergic(allergic);
        pet.setSpecialAttention(specialAttention);
        pet.setOwner(owner);

        controlPersis.save(owner, pet);
    }
}
