package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import entities.Phone;
import exceptions.MissingInput;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotFoundException;

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

    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return new PersonsDTO(em.createNamedQuery("Person.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public PersonDTO getPerson(long phone) {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, phone);
            if (p == null) {
                throw new NotFoundException("No person with the provided phone was found");
            }
            PersonDTO personDTO = new PersonDTO(p);
            
            return personDTO;
        } finally {
            em.close();
        }
    }

    public PersonDTO updatePerson(PersonDTO p) {

        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, p.getId());
        if (person == null) {
            throw new NotFoundException("Person ID: " + p.getId() + " not found");
        }
        person.setEmail(p.getEmail());
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());
        
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    public PersonDTO deletePerson(Long id) {
        EntityManager em = emf.createEntityManager();
        Person pp = em.find(Person.class, id);
        try {
            em.getTransaction().begin();
            em.remove(pp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(pp);
    }

    public PersonDTO addPerson(PersonDTO newPerson) throws MissingInput {

        EntityManager em = emf.createEntityManager();

        Person person = new Person(newPerson.getEmail(), newPerson.getFirstName(), newPerson.getLastName());
        CityInfo cityInfo = new CityInfo(newPerson.getZip(), newPerson.getCity());
        Address address = new Address(newPerson.getStreet(), newPerson.getAdditionalInfo(), cityInfo);
        person.setAddress(address);
        Phone phone = new Phone();
        
        phone.setPhoneNumber(Integer.parseInt(newPerson.getPhoneNumbers()));
        //phone.setPhoneNumber(Integer.parseInt(newPerson.getPhoneNumbers()));
//        phone.setPhoneNumber(12345678);
        
        
        phone.setDescription("Work");
        phone.setPerson(person);
        person.addPhone(phone);
        
        
        if (newPerson.getFirstName().length() == 0 || newPerson.getLastName().length() == 0 ) {
            throw new MissingInput("Missing input");
        }
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

}
