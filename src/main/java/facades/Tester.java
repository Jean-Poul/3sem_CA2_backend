package facades;

import entities.Address;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Tester {

    public static void main(String[] args) {

        //Create emf pointing to the dev-database
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Mick@hotmale.com", "Mick", "Larsen");
        Person p2 = new Person("Hejsa@med.dig", "Per", "Fra CPH");

        Address a1 = new Address("Street 1", "2800");
        Address a2 = new Address("Street 2", "2900");

        p2.setAddress(a1);
        p1.setAddress(a2);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            //Remove tester
            //em.remove(p1);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}