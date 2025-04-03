package org.logic;

import org.persistence.PersistenceController;
import org.persistence.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
