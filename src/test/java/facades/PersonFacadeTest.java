package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import utils.EMF_Creator;
import entities.Person;
import exceptions.MissingInput;
import java.util.List;
import javassist.NotFoundException;
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
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    
    private Person p1;
    private Person p2;

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
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Mick@hotmale.com", "Mick", "Larsen"); 
        p2 = new Person("Hejsa@med.dig", "Per", "Fra CPH");
        try {
            em.getTransaction().begin();
            
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);

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
    public void testPersonCount() {
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
    public void testGetPersonByPhone() throws NotFoundException {

        PersonDTO personDTO = facade.getPerson((int) p1.getId());
        assertEquals("Mick", personDTO.getFirstName());

    }
    
    @Test
    public void testAddPerson() throws MissingInput  {
        Person person = new Person("Oof@ouch.now", "McJackie", "Potato", "street", "2800");
        PersonDTO personDTO = new PersonDTO(person);
        facade.addPerson(personDTO);
        assertEquals(3, facade.getPersonCount());
    }

}
