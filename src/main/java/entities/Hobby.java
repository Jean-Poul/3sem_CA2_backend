
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "hobby")
//@NamedQuery(name = "hobby.deleteAllRows", query = "DELETE from hobby")
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(length = 50)
    private String name;
    
    @Column(name = "wikilink", length = 150)
    private String wikiLink;
    
    @Column(length = 50)
    private String category;
    
    @Column(length = 50)
    private String type;
    
//    @ManyToMany
//    //TODO person

    public Hobby() {
    }

    public Hobby(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
