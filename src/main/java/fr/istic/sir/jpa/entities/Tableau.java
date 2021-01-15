package fr.istic.sir.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tableau {

    private long id;
    private String libelle ;

    private List<Section> section;


    public Tableau() { }

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

    @OneToMany(mappedBy = "tab")
    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }
}
