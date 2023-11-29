package vn.edu.iuh.fit.backend.repositories;

import vn.edu.iuh.fit.backend.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerRepository {
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CustomerRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertCust(Customer customer) {
        try {
            trans.begin();
            em.persist(customer);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public List<Customer> getAllCust() {
        return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
    }

    public void updateCust(Customer customer) {
        try {
            trans.begin();
            em.merge(customer);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public void deleteCust(long custId) {
        try {
            trans.begin();
            Customer customer = em.find(Customer.class, custId);
            if (customer != null) {
                em.remove(customer);
            }
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public Customer findCustById(long custId) {
        return em.find(Customer.class, custId);
    }
}
