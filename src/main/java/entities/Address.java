package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String street;

    @Column(length = 150)
    private String zipcode;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

//    @ManyToOne
//    CityInfo cityInfo;
    
    public Address() {
    }

    public Address(String street, String zipcode) {
        this.street = street;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    
 
    public List<Person> getPersons() {
        return persons;
    }

    public void AddPerson(Person person) {
        this.persons.add(person);
        if (person != null) {
            person.setAddress(this);
        }
    }

}
