package org.persistence;

import org.logic.Owner;
import org.logic.Pet;

public class PersistenceController {
    OwnerJpaController ownerJPA = new OwnerJpaController();
    PetJpaController petJPA = new PetJpaController();


    public void save(Owner owner, Pet pet) {
        ownerJPA.create(owner);
        petJPA.create(pet);
    }
}
