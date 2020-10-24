package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import exceptions.MissingInput;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello - You have connected to our API\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        PersonsDTO person = FACADE.getAllPersons();
        return GSON.toJson(person);
    }

    @Path("/{phone}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByPhone(@PathParam("phone") int phone) {
        return GSON.toJson(FACADE.getPerson(phone));
    }
    
    @Path("hobby/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByName(@PathParam("name") String name) {
        return Response.ok().entity(GSON.toJson(FACADE.getHobbyByName(name))).build();
    }
    
    @PUT
    @Path("update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") Long id, String person) {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO updatePerson = FACADE.updatePerson(personDTO);
        return GSON.toJson(updatePerson);
    }
    
    @PUT
    @Path("addhobby/{personId}/{hobbyId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addHobbyToPerson(@PathParam("personId") Long personId, @PathParam("hobbyId") Long hobbyId) {
        FACADE.addHobby(personId, hobbyId);
        return "Alt OK";
    }
        
    
    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") Long id) {
        PersonDTO pDelete = FACADE.deletePerson(id);
        return "{\"status\" : \"deleted\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addPerson(String person) throws MissingInput {

        PersonDTO newPerson = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPersonDTO = FACADE.addPerson(newPerson);
        return Response.ok().entity(GSON.toJson(newPersonDTO)).build();        

    }    

    
    
    
}
