package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String zip;
    private String city;
    private List<Phone> phones;
    private List<Hobby> hobbies;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.street = person.getAddress().getStreet();
        this.zip = person.getAddress().getCityInfo().getZipCode();
        this.city = person.getAddress().getCityInfo().getCity();
        this.phones = person.getPhones();
        this.hobbies = person.getHobbies();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phone) {
        this.phones = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

}
