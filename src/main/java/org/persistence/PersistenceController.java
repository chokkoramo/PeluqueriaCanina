package org.persistence;

import org.logic.Controller;
import org.logic.Owner;
import org.logic.Pet;
import org.persistence.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void deleteRow(int numOwner) {
        try{
            petJPA.destroy(numOwner);
        } catch (NonexistentEntityException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
