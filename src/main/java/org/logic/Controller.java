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

}
