package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonsDTO {

    List<PersonDTO> all = new ArrayList();

    public PersonsDTO(List<Person> personEntities) {
        personEntities.forEach((p) -> {
            all.add(new PersonDTO(p));
        });
    }

    public List<PersonDTO> getAll() {
        return all;
    }

    public void setAll(List<PersonDTO> all) {
        this.all = all;
    }
}
