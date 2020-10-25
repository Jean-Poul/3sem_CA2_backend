package facades;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
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
        Person p2 = new Person("Asterix@Gallerne.fr", "Asterix", "Idefix");
        Person p3 = new Person("Obelix@Gallerne.fr", "Obelix", "Bauta");
        Person p4 = new Person("fuk@yu.kr", "Fuk", "yu");
        Person p5 = new Person("fuk@mi.kr", "Fuk", "Mi");
        Person p6 = new Person("Ivanna@humpalot.ru", "Ivanna", "Humpalot");
        Person p7 = new Person("Biggus@Dickus.it", "Biggus", "Dickus");
        Person p8 = new Person("Hej@med.dig", "Per", "Fra CPH");
        Person p9 = new Person("Irene@Olsen.dk", "Irene", "Olsen");
        Person p10 = new Person("Theis@testesen.com", "Theis", "Larsen");

        CityInfo cInf1 = new CityInfo("3000");
        CityInfo cInf2 = new CityInfo("4000");
        CityInfo cInf3 = new CityInfo("6000");
        CityInfo cInf4 = new CityInfo("3460");
        CityInfo cInf5 = new CityInfo("2860");
        CityInfo cInf6 = new CityInfo("3200");
        CityInfo cInf7 = new CityInfo("1650");
        CityInfo cInf8 = new CityInfo("4600");
        CityInfo cInf9 = new CityInfo("2400");

        Address a1 = new Address("Teglværksvej 74", "Hunden bider", cInf1);
        Address a2 = new Address("Ericavej 147", "st. tv", cInf2);
        Address a3 = new Address("Istedgade 4", "Bag det røde gardin", cInf3);
        Address a4 = new Address("Hovedevejen 43", "10. dør på venstrehånd", cInf4);
        Address a5 = new Address("Rybjerg alle 93", "1, th", cInf5);
        Address a6 = new Address("Gaden 22", "første flise til højre", cInf6);
        Address a7 = new Address("Istedgade 3", "Kælderen", cInf7);
        Address a8 = new Address("Robådsgade 34", "3, mf", cInf8);
        Address a9 = new Address("Kanalen 3", "3, TV", cInf9);

        Phone ph1 = new Phone(38792048, "home");
        Phone ph2 = new Phone(38640671, "work");
        Phone ph3 = new Phone(38647360, "work");
        Phone ph4 = new Phone(52655368, "work");
        Phone ph5 = new Phone(91963064, "home");
        Phone ph6 = new Phone(38641650, "work");
        Phone ph7 = new Phone(22294019, "work");
        Phone ph8 = new Phone(52782366, "work");
        Phone ph9 = new Phone(39698484, "home");
        Phone ph10 = new Phone(69696969, "work");
        Phone ph11 = new Phone(42042069, "work");
        Phone ph12 = new Phone(44107040, "work");

        p1.setAddress(a1);
        p2.setAddress(a2);
        p3.setAddress(a3);
        p4.setAddress(a7);
        p5.setAddress(a7);
        p6.setAddress(a4);
        p7.setAddress(a5);
        p8.setAddress(a6);
        p9.setAddress(a9);
        p10.setAddress(a8);

        p1.addPhone(ph1);
        p1.addPhone(ph2);
        p2.addPhone(ph3);
        p3.addPhone(ph4);
        p4.addPhone(ph5);
        p5.addPhone(ph6);
        p6.addPhone(ph7);
        p6.addPhone(ph8);
        p7.addPhone(ph9);
        p8.addPhone(ph10);
        p9.addPhone(ph11);
        p10.addPhone(ph12);

        Hobby h1 = new Hobby();
        Hobby h2 = new Hobby();
        Hobby h3 = new Hobby();
        Hobby h4 = new Hobby();
        Hobby h5 = new Hobby();
        Hobby h6 = new Hobby();
        Hobby h7 = new Hobby();
        Hobby h8 = new Hobby();
        Hobby h9 = new Hobby();
        Hobby h10 = new Hobby();
        Hobby h11 = new Hobby();
        Hobby h12 = new Hobby();
        Hobby h13 = new Hobby();
        Hobby h14 = new Hobby();

        try {
            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            h1 = em.find(Hobby.class, 2L);
            h2 = em.find(Hobby.class, 122L);
            h3 = em.find(Hobby.class, 301L);
            h4 = em.find(Hobby.class, 23L);
            h5 = em.find(Hobby.class, 11L);
            h6 = em.find(Hobby.class, 322L);
            h7 = em.find(Hobby.class, 36L);
            h8 = em.find(Hobby.class, 99L);
            h9 = em.find(Hobby.class, 69L);
            h10 = em.find(Hobby.class, 420L);
            h11 = em.find(Hobby.class, 244L);
            h12 = em.find(Hobby.class, 299L);
            h13 = em.find(Hobby.class, 369L);
            h14 = em.find(Hobby.class, 420L);

            p1.AddHobby(h1);
            p2.AddHobby(h2);
            p2.AddHobby(h12);
            p3.AddHobby(h3);
            p3.AddHobby(h13);
            p4.AddHobby(h4);
            p4.AddHobby(h14);
            p5.AddHobby(h5);
            p6.AddHobby(h6);
            p6.AddHobby(h11);
            p7.AddHobby(h7);
            p8.AddHobby(h8);
            p8.AddHobby(h3);
            p9.AddHobby(h9);
            p10.AddHobby(h10);
            p10.AddHobby(h9);

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
            em.persist(p6);
            em.persist(p7);
            em.persist(p8);
            em.persist(p9);
            em.persist(p10);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
