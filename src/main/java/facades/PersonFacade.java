package facades;

import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPerson(Long id){
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                //throw new PersonNotFoundException("No person with the provided id found");
            }
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }
    
    public PersonDTO addPerson(PersonDTO newPerson) {
        EntityManager em = emf.createEntityManager();

        Person person = new Person(newPerson.getEmail(), newPerson.getFirstName(), newPerson.getLastName());
        CityInfo cityInfo = new CityInfo(newPerson.getZip());
        Address address = new Address(newPerson.getStreet(), newPerson.getAdditionalInfo(), cityInfo);
        person.setAddress(address);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }    

}
