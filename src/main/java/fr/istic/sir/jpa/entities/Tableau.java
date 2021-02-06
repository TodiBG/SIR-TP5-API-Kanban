package fr.istic.sir.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Tableau implements Serializable {

    private long id;
    private String libelle ;

    private List<Section> sections;


    protected Tableau() { }

    public Tableau(String libelle) {
        this.libelle = libelle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tab")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> section) {
        this.sections = section;
    }
}
