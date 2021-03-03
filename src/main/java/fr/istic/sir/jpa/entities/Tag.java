package fr.istic.sir.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag implements Serializable {

    private long id;
    private String libelle ;
    private List<Fiche> fiches = new ArrayList<>();

    /** un constructeur vide ayant une visibité protected ou public est neceesaire pour Hibernate **/
    protected Tag() {}

    /**
     * Le constructeur principal
     * @param libelle , le libelé du Tag
     */
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

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiche) {
        this.fiches = fiche;
    }

    public void addFiche(Fiche fiche){
        //On ne veut pas de doublons dans la liste
        if(  !this.fiches.contains(fiche)) {
            this.fiches.add(fiche);
        }
    }

    public void removeFiche(Fiche fiche){

        if( this.fiches.contains(fiche)) {
            this.fiches.remove(fiche);
            fiche.getTags().remove(this);
        }
    }
}
