package dto;

import entities.Person;
import entities.Phone;
import java.util.List;

public class PersonDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String zipcode;
    private String city;
    private List phone;
    private List hobbies;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.street = person.getAddress().getStreet();
        this.zipcode = person.getAddress().getCityInfo().getZipCode();
//        this.city = person.getAddress().getCityInfo().getCity();
//        this.phone = person.getPhones();
//        this.hobbies = person.getHobbies();
        this.id = person.getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

//    public List getPhone() {
//        return phone;
//    }
//
//    public void setPhone(List phone) {
//        this.phone = phone;
//    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zipcode;
    }

    public void setZip(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

}
