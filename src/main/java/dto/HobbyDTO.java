package dto;

import entities.Hobby;

public class HobbyDTO {

    private Long id;
    private String name;
    private String wikiLink;
    private String category;
    private String type;

    public HobbyDTO() {
    }

    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.wikiLink = hobby.getWikiLink();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
