package org;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.gui.Principal;
import org.gui.UploadingData;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PeluqueriaCaninaPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("¡Conexión a JPA exitosa!");

        em.close();
        emf.close();


        Principal windowPrincipal = new Principal();
        windowPrincipal.setVisible(true);
        windowPrincipal.setLocationRelativeTo(null);
    }
}