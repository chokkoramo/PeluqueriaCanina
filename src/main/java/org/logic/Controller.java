package org.logic;

import org.persistence.PersistenceController;

import java.util.List;

public class Controller {
    PersistenceController controlPersis = new PersistenceController();


    public void saveOwnerWithPets(String ownerName, String ownerPhone, List<Pet> pets) {
        Owner owner = new Owner();
        owner.setOwnerName(ownerName);
        owner.setOwnerPhone(ownerPhone);
        owner.setPets(pets);

        // Asociar cada mascota con el dueño
        for (Pet pet : pets) {
            pet.setOwner(owner);
        }

        controlPersis.save(owner);
    }

    public List<Pet> getPetList() {
        return controlPersis.getPetList();
    }

    public void deleteRow(int numOwner) {
        controlPersis.deleteRow(numOwner);
    }

    public List<Owner> getAllOwners() {
        return controlPersis.getAllOwners();
    }

    public void addPetsToExistingOwner(int idOwner, List<Pet> petList) {
        controlPersis.addPetsToExistingOwner(idOwner, petList);
    }

    public Pet getAllPets(int numOwner) {
        return controlPersis.getAllPets(numOwner);
    }
}
