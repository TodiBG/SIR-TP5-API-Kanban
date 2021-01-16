package fr.istic.sir.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    private long id;
    private String prenom ;
    private String nom;
    private List<Fiche> fiche;

    protected Utilisateur() {  }

    public Utilisateur(String prenom, String nom ) {
        this.prenom = prenom;
        this.nom = nom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @OneToMany(mappedBy = "utilisateur")
    public List<Fiche> getFiche() {
        return fiche;
    }

    public void setFiche(List<Fiche> fiche) {
        this.fiche = fiche;
    }
}
