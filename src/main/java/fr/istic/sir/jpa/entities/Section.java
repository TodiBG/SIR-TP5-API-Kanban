package fr.istic.sir.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Section implements Serializable {

    private long id;

    private String libelle;



    private List<Fiche> fiches ;
    private Tableau tab;

    protected Section() { }

    public Section(String libelle, List<Fiche> fiches ,Tableau tab ) {
        this.libelle = libelle;
        this.fiches = fiches;
        this.tab = tab;

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


    @ManyToOne
    public Tableau getTab() {
        return tab;
    }

    public void setTab(Tableau tab) {
        this.tab = tab;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "section")
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiches) {
        this.fiches = fiches;
    }
}
