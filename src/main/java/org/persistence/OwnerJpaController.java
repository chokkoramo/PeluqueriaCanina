package org.persistence;

import jakarta.persistence.*;

import java.io.Serializable;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.logic.Owner;
import org.persistence.exceptions.NonexistentEntityException;

public class OwnerJpaController implements Serializable {

    public OwnerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public OwnerJpaController() {
        emf= Persistence.createEntityManagerFactory("PeluqueriaCaninaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Owner owner) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Owner owner) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            owner = em.merge(owner);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = owner.getIdOwner();
                if (findOwner(id) == null) {
                    throw new NonexistentEntityException("The owner with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Owner owner;
            try {
                owner = em.getReference(Owner.class, id);
                owner.getIdOwner();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The owner with id " + id + " no longer exists.", enfe);
            }
            em.remove(owner);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Owner> findOwnerEntities() {
        return findOwnerEntities(true, -1, -1);
    }

    public List<Owner> findOwnerEntities(int maxResults, int firstResult) {
        return findOwnerEntities(false, maxResults, firstResult);
    }

    private List<Owner> findOwnerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Owner.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Owner findOwner(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Owner.class, id);
        } finally {
            em.close();
        }
    }

    public int getOwnerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Owner> rt = cq.from(Owner.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
