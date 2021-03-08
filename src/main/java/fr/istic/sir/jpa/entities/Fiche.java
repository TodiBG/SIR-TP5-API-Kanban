package fr.istic.sir.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Fiche  implements Serializable {

    private long id;
    private String libelle;
    private Date dateButoire;
    private User user;
    private int temps;
    private List<Tag> tags  = new ArrayList<>();
    private String lieu;
    private String url = "";
    private String note;
    private Section section ;



    private Tableau tab ;

    /** un constructeur vide ayant une visibité protected ou public est neceesaire pour Hibernate **/
    protected Fiche() {}

    /**
     *Un autre constructeur
     * @param libelle le libelé de la fiche
     * @param dateButoire, la date bitoire
     * @param user, l'utilisateur de la fiche ( un collaboratoire )
     * @param temps , temps
     * @param lieu
     * @param url
     * @param note, Une note explicative sur la fiche. c'est different de Tag
     */
    public Fiche(String libelle, Date dateButoire, User user, int temps, String lieu, String url, String note) {
        this.libelle = libelle;
        this.dateButoire = dateButoire;
        this.user = user;
        this.temps = temps;
        this.lieu = lieu;
        this.url = url;
        this.note = note;
    }



     /** Un autre constructeur
     * @param libelle le libelé de la fiche
     * @param dateButoire, la date bitoire
     * @param user, l'utilisateur de la fiche ( un collaboratoire )
            * @param temps , temps
     * @param lieu
     * @param url
     * @param note, Une note explicative sur la fiche. c'est different de Tag
      * @param tags liste des Tags
      * @param section la section
      * @param tab le tableau où se troouve la fiche
      * **/
    public Fiche(String libelle, Date dateButoire, User user, int temps, String lieu, String url, String note, Tableau tab, Section section, List<Tag> tags) {
        this.libelle = libelle;
        this.dateButoire = dateButoire;
        this.user = user;
        this.temps = temps;
        this.lieu = lieu;
        this.url = url;
        this.note = note;
        this.section = section ;
        this.tags = tags ;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        if(this.url.equals("")) {
            this.url = "http://localhost:8080/fiches/" + id;
        }
        return id;
    }

    public void setId(long id) {
        this.id = id;
        if(this.url.equals("")) {
            this.url = "http://localhost:8080/fiches/" + id;
        }
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateButoire() {
        return dateButoire;
    }

    public void setDateButoire(Date dateButoire) {
        this.dateButoire = dateButoire;
    }

    public void setDateButoire(String dateButoire) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);

        if( dateButoire.contains("/") || dateButoire.contains("-")){
            try {this.dateButoire = formatter.parse(dateButoire.replaceAll("/","-")   );} catch (ParseException e) { e.printStackTrace(); }
        }else{
            long date = Long.parseLong(dateButoire) ;
            this.dateButoire  = new Date(date) ;
        }
    }


    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tag) {
        this.tags = tag;
    }

    public void addTag(Tag tag){
        //On ne veut pas de doublon dans cette liste
        if( ! this.tags.contains(tag)) {
            this.tags.add(tag) ;
        }

    }

    public void removeTag(Tag tag){
        if( this.tags.contains(tag)) {
            this.tags.remove(tag);
            //Assurer la coherence des données
            tag.getFiches().remove(this);
        }
    }



    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }


    @ManyToOne()
    public Tableau getTab() {
        return tab;
    }

    public void setTab(Tableau tab) {
        this.tab = tab;
    }


}
