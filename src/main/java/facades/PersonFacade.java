package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exceptions.MissingInput;
import java.util.ArrayList;
import java.util.List;
import exceptions.NotFound;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


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

    public long getPersonCount() throws NotFound {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();

            if (count == 0) {
                throw new NotFound("No records were found in the database");
            }

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


    public PersonDTO getPerson(long id) throws NotFound {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);

            if (p == null) {
                throw new NotFound("No person with the provided id was found");
            }

            PersonDTO personDTO = new PersonDTO(p);

            return personDTO;
        } finally {
            em.close();
        }
    }


    public List<PersonDTO> getPersonByPhone(String phone) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query;
        query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.phoneNumber LIKE :phone", Person.class);
        query.setParameter("phone", "%" + phone + "%");
        List<Person> persons = query.getResultList();
        List<PersonDTO> personDTOs = new ArrayList();
        persons.forEach((Person person) -> {
            personDTOs.add(new PersonDTO(person));
        });
        return personDTOs;
    }
    

    public List<HobbyDTO> getHobbyByName(String name) throws NotFound {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.name LIKE :name", Hobby.class);
        query.setParameter("name", "%" + name + "%");
        List<Hobby> hobbies = query.getResultList();
        List<HobbyDTO> hobbyDTOs = new ArrayList();

        if (hobbies == null || hobbyDTOs == null) {
            throw new NotFound("No hobby with the provided title was found");
        }

        hobbies.forEach((Hobby hobby) -> {
            hobbyDTOs.add(new HobbyDTO(hobby));
        });
        return hobbyDTOs;
    }


    public String addHobby(Long personID, Long hobbyId) {
        EntityManager em = emf.createEntityManager();

        Hobby hobby = new Hobby();
//        Long Id = new Long(hobbyId);

        try {
            em.getTransaction().begin();
            hobby = em.find(Hobby.class, hobbyId);
            Person p = em.find(Person.class, personID);
            p.AddHobby(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "All ok!";
    }


    public PersonDTO updatePerson(PersonDTO p) throws NotFound {

        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, p.getId());
        
        if (person == null) {
            throw new NotFound("Person ID: " + p.getId() + " not found");
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

    public PersonDTO deletePerson(Long id) throws NotFound {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);

        if (p.getId() == 0) {
            throw new NotFound("No person with the provided id was found");
        }

        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    public PersonDTO addPerson(PersonDTO newPerson) throws MissingInput {

        EntityManager em = emf.createEntityManager();

        Person person = new Person(newPerson.getEmail(), newPerson.getFirstName(), newPerson.getLastName());
        CityInfo cityInfo = new CityInfo(newPerson.getZip(), newPerson.getCity());
        Address address = new Address(newPerson.getStreet(), newPerson.getAdditionalInfo(), cityInfo);
        person.setAddress(address);
        Phone phone = new Phone();

        phone.setPhoneNumber(Integer.parseInt(newPerson.getPhoneNumbers()));

        phone.setDescription("Work");
        phone.setPerson(person);
        person.addPhone(phone);

        if (    newPerson.getFirstName().length() == 0 || newPerson.getLastName().length() == 0 || 
                newPerson.getEmail().length() == 0 || newPerson.getPhoneNumbers() == null ||
                newPerson.getZip().length() == 0 || newPerson.getStreet().length() == 0 ||
                newPerson.getAdditionalInfo().length() == 0
                ) {
            throw new MissingInput("An input field is missing data");

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
