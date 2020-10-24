package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

public class PersonDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String zip;
    private String city;
    private String additionalInfo;
    //private List<Phone> phone;
    //private List<Hobby> hobbies;
    private String phoneNumbers = "";
    private String hobbies = "";

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.street = person.getAddress().getStreet();
        this.zip = person.getAddress().getCityInfo().getZipCode();
        this.city = person.getAddress().getCityInfo().getCity();
        this.additionalInfo = person.getAddress().getAdditionalinfo();
        this.phoneNumbers = allPhoneNos(person);
        //this.hobbies = person.getHobbies();
        this.hobbies = allHobbies(person);
    }

    static public String allPhoneNos(Person person) {
        int arrSize = person.getPhones().size();
        StringBuilder phoneString = new StringBuilder("");
        for (int i = 0; i < arrSize; i++) {
            phoneString.append(person.getPhones().get(i).getPhoneNumber());
            phoneString.append(", ");
        }
        phoneString.delete(phoneString.length() - 2, phoneString.length());
        String finString = phoneString.toString();
        return finString;
    }

    static public String allHobbies(Person person) {
        int arrSize = person.getHobbies().size();
        StringBuilder hobbyString = new StringBuilder("");
        String finString = "";

        if (person.getHobbies().isEmpty()) {
            for (int i = 0; i < arrSize; i++) {
                hobbyString.append(person.getHobbies().get(i).getName());
                hobbyString.append(", ");
            }
            //hobbyString.delete(hobbyString.length() - 2, hobbyString.length());
            finString = hobbyString.toString();
        } else {
            Hobby h1 = new Hobby("", "", "", "");
            person.AddHobby(h1);
            finString = "";
        }
        return finString;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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

//    public List getHobbies() {
//        return hobbies;
//    }
//
//    public void setHobbies(List hobbies) {
//        this.hobbies = hobbies;
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
