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
        this.prenom = prenom.trim();
        this.nom = nom.trim();
        this.email = email.trim().toLowerCase() ;
    }

    @Id
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    @Column(nullable = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom.trim();
    }

    @Column(nullable = false)
    public String getNom() {
        return nom.trim();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiche) {
        this.fiches = fiche;
    }
}
