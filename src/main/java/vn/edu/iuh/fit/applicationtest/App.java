package vn.edu.iuh.fit.applicationtest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_week_2");
        EntityManager em = emf.createEntityManager();
    }
}
