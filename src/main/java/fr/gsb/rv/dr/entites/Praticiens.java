package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

public class Praticiens {

    private int numero;
    private String nom;
    private String prenom;
    private String ville;
    private Double coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int dernierCoefConfiance;

    public Praticiens(int numero, String nom, String prenom, String ville, Double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public Praticiens() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Double getCoefNotoriete() {
        return coefNotoriete;
    }

    public void setCoefNotoriete(Double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Praticiens{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", coefNotoriete=" + coefNotoriete +
                ", dateDerniereVisite=" + dateDerniereVisite +
                ", dernierCoefConfiance=" + dernierCoefConfiance +
                '}';
    }


}
