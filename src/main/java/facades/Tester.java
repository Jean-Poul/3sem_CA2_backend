package facades;

import entities.Address;
import entities.CityInfo;
import entities.Person;
import entities.Phone;
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
        Person p3 = new Person("test@testesen.com", "Testmand", "Larsen");

        Address a1 = new Address("Street 1", "2, th", "2800");
        Address a2 = new Address("Street 2", "3, mf", "3000");

        p2.setAddress(a1);
        p1.setAddress(a2);

        Phone ph1 = new Phone(12345678, "home");
        Phone ph2 = new Phone(22222222, "work");
        Phone ph3 = new Phone(87654321, "home");

        p1.AddPhone(ph1);
        p1.AddPhone(ph2);
        p2.AddPhone(ph3);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}
