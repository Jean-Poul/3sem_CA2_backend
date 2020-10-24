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

        CityInfo cInf1 = new CityInfo("3000");
        CityInfo cInf2 = new CityInfo("4000");
        CityInfo cInf3 = new CityInfo("6000");

        Address a1 = new Address("Street 1", "2, th", cInf2);
        Address a2 = new Address("Street 2", "3, mf", cInf3);
        Address a3 = new Address("Street 3", "3, TV", cInf1);

        p2.setAddress(a1);
        p1.setAddress(a2);
        p3.setAddress(a3);

        // ** GIVER STACK OVERFLOW **
        Phone ph1 = new Phone(11111111, "home");
        Phone ph2 = new Phone(22222222, "work");
        Phone ph3 = new Phone(33333333, "work");
        Phone ph4 = new Phone(44444444, "work");

        p2.addPhone(ph1); //En person kan have flere telefonnumre, men ikke omvendt
        p2.addPhone(ph2); //En person kan have flere telefonnumre, men ikke omvendt
        p1.addPhone(ph3);
        p3.addPhone(ph4);



//        ** TILFØJER TIL HOBBY TABELLEN - IKKE KORREKT ** 
//        Hobby h1 = new Hobby("3D-udskrivning", "https://en.wikipedia.org/wiki/3D_printing", "Generel", "Indendørs");
//        p1.AddHobby(h1);

        try {
            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
