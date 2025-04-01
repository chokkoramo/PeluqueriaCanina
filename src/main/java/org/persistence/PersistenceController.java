package org.persistence;

import org.logic.Owner;
import org.logic.Pet;

import java.util.List;

public class PersistenceController {
    OwnerJpaController ownerJPA = new OwnerJpaController();
    PetJpaController petJPA = new PetJpaController();


    public void save(Owner owner) {
        try {
            for (Pet pet : owner.getPets()) {
                pet.setOwner(owner);
            }

            // CascadeType.ALL
            ownerJPA.create(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getPetList() {

        return petJPA.findPetEntities();
    }
}
