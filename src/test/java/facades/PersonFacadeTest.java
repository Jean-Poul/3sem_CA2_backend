package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Hobby;
import utils.EMF_Creator;
import entities.Person;
import exceptions.MissingInput;
import exceptions.NotFound;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    private Person p1;
    private Person p2;
    
    private Hobby h1;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        emf.close();
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Mick@hotmale.com", "Mick", "Larsen");
        p2 = new Person("Hejsa@med.dig", "Per", "Kringel");
        
        h1 = new Hobby("Fodbold", "https://en.wikipedia.org/wiki/Fodbold", "Generel", "Udendørs");

        try {
            em.getTransaction().begin();

            em.createNamedQuery("Person.deleteAllRows").executeUpdate();

            em.persist(p1);
            em.persist(p2);
            
            em.persist(h1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testPersonCount() throws NotFound {
        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetAllPersons() {
        PersonsDTO personsDTO = facade.getAllPersons();
        List<PersonDTO> list = personsDTO.getAll();
        assertThat(list, everyItem(Matchers.hasProperty("lastName")));
        assertThat(list, Matchers.hasItems(Matchers.<PersonDTO>hasProperty("firstName", is("Kurt")),
                Matchers.<PersonDTO>hasProperty("firstName", is("Per"))
        )
        );
    }

    @Test
    public void testGetPersonByPhone() throws NotFound {
        PersonDTO personDTO = facade.getPerson((int) p1.getId());
        assertEquals("Mick", personDTO.getFirstName());
    }

    @Test
    public void testAddPerson() throws MissingInput, NotFound {
        Person person = new Person("test@tester.nu", "J-P", "L-M");
        PersonDTO personDTO = new PersonDTO(person);
        facade.addPerson(personDTO);
        assertEquals(3, facade.getPersonCount());
    }
    
    @Test
    public void testgetHobbyByName() throws NotFound {
        HobbyDTO hobby = new HobbyDTO();
        List<HobbyDTO> hobbyList;
        String expected = h1.getName();
        
        assertEquals(expected, facade.getHobbyByName("fodbold"));
    }

}
