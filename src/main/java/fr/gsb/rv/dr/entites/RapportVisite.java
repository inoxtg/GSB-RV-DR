package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

public class RapportVisite {

    private Visiteur leVisiteur;
    private Praticiens lePraticien;
    private int numero;
    private LocalDate dateVisite;
    private LocalDate dateRedaction;
    private String bilan;
    private String motif;
    private int coefConfiance;
    private boolean lu;

    public RapportVisite(Visiteur leVisiteur, Praticiens lePraticien, int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.leVisiteur = leVisiteur;
        this.lePraticien = lePraticien;
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public RapportVisite() {
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public Praticiens getLePraticien() {
        return lePraticien;
    }

    public void setLePraticien(Praticiens lePraticien) {
        this.lePraticien = lePraticien;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public LocalDate getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    @Override
    public String toString() {
        return "RapportVisite{" +
                "leVisiteur=" + leVisiteur +
                ", lePraticien=" + lePraticien +
                ", numero=" + numero +
                ", dateVisite=" + dateVisite +
                ", dateRedaction=" + dateRedaction +
                ", bilan='" + bilan + '\'' +
                ", motif='" + motif + '\'' +
                ", coefConfiance=" + coefConfiance +
                ", lu=" + lu +
                '}';
    }
}
