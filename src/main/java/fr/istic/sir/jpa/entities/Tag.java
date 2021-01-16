package fr.istic.sir.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    private long id;
    private String libelle ;
    private List<Fiche> fiches = new ArrayList<>();

    protected Tag() {}

    public Tag(String libelle) {
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

    @ManyToMany
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiche) {
        this.fiches = fiche;
    }

    public void addFiche(Fiche fiche){
        this.fiches.add(fiche) ;
    }

    public void removeFiche(Fiche fiche){

        if( this.fiches.contains(fiche)) {
            this.fiches.remove(fiche);
        }
    }
}
