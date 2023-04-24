package fr.gsb_rv_dr.entites;

public class Visiteur {
    private String matricule;
    private String nom;
    private String prenom;
    private Integer nbVisite = 0;
    private Integer critereTri = 0;


    public Visiteur(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getNbVisite() {
        return nbVisite;
    }

    public void setNbVisite(Integer nbVisite) {
        this.nbVisite = nbVisite;
    }

    public Integer getCritereTri() {
        return critereTri;
    }

    public void setCritereTri(Integer critereTri) {
        this.critereTri = critereTri;
    }

    public Visiteur(String matricule, String nom, String prenom, Integer critereTri) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.critereTri = critereTri;
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return  nom.toUpperCase() + " " + prenom + " " + "(" + matricule + ")" ;
    }
}

