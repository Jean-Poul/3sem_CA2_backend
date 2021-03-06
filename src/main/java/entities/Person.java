package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person"),
    @NamedQuery(name = "Person.getAllRows", query = "SELECT p from Person p")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Person_ID")
    private long id;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    List<Phone> phones;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons", cascade = CascadeType.PERSIST)
    List<Hobby> hobbies;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
    
    

    public Person() {
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = new ArrayList<>();
        this.hobbies = new ArrayList<>();
        this.created = new Date();
    }

    public Person(String email, String firstName, String lastName, String street, String zipcode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        if (phone != null) {
            phone.setPerson(this);
        }
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void AddHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void RemoveHobby(Hobby hobby) {
        if (hobby != null) {
            hobbies.remove(hobby);
            hobby.getPersons().remove(this);

        }
    }

    public long getId() {
        return id;
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

    public void setAddress(Address address) {
        this.address = address;
//         if(address != null) {
//            address.AddPerson(this);
//        }
    }

    public Address getAddress() {
        return address;
    }

}
