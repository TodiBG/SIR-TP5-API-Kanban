package fr.istic.sir.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    private String email;
    private String prenom ;
    private String nom;
    private List<Fiche> fiches;

    protected User() {  }

    public User(String prenom, String nom , String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email ;
    }

    @Id
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", fetch=FetchType.LAZY)
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiche) {
        this.fiches = fiche;
    }
}
