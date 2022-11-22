package fr.gsb.rv.dr.entites;

public class Praticiens {
    private String nom;
    private String prenom;
    private int numero;

    public Praticiens(String nom, String prenom, int numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    public Praticiens() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Praticiens{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero=" + numero +
                '}';
    }
    public void set(Praticiens pra){
        this.nom = pra.nom;
        this.prenom = pra.prenom;
        this.numero = pra.numero;
    }
}
