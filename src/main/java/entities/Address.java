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
    private String additionalinfo;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CityInfo cityinfo;
    
    public Address() {
    }

    public Address(String street, String additionalinfo) {
        this.street = street;
        this.additionalinfo = additionalinfo;
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

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public CityInfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(CityInfo cityinfo) {
        this.cityinfo = cityinfo;
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
