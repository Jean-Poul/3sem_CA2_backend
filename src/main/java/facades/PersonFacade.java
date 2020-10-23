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
            return new PersonsDTO(em.createQuery("SELECT p FROM Person p", Person.class).getResultList());
        } finally {
            em.close();
        }
    }

    public PersonDTO getPerson(long phone) {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, phone);
            if (p == null) {
                //throw new PersonNotFoundException("No person with the provided id found");
            }
            PersonDTO personDTO = new PersonDTO(p);
            
            return personDTO;
        } finally {
            em.close();
        }
    }

    public PersonDTO updatePerson(PersonDTO p) {
//        if ((p.getFirstName().length() == 0) || (p.getLastName().length() == 0) || (p.getPhone().length() == 0)) {
//            throw new MissingInputException("First Name, Last Name and/or Phone is missing");
//        }
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, p.getId());
//        if (person == null) {
//            throw new PersonNotFoundException("Person ID: " + p.getId() + " not found");
//        }
        person.setEmail(p.getEmail());
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());

//        person.setStreet(p.getStreet());
//        person.setZipcode(p.getZip());
//        person.getAddress().setStreet(p.getStreet());
//        person.getAddress().getCityInfo().setZipCode(p.getZip());
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
        phone.setPhoneNumber(person.getPhones().get(0).getPhoneNumber());
        //phone.setPhoneNumber(12345678);
        
        
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
