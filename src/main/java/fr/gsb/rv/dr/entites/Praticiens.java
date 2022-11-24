package fr.gsb.rv.dr.entites;

public class Praticiens {
    private String nom;
    private String prenom;
    private int numero;
    private String ville;
    private Double coefNotoriete;

    public Praticiens(String nom, String prenom, int numero, String ville, Double coefNotoriete) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
    }

    public Praticiens() {
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    @Override
    public String toString() {
        return "Praticiens{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero=" + numero +
                ", ville='" + ville + '\'' +
                ", coefNotoriete=" + coefNotoriete +
                '}';
    }

    public void set(Praticiens pra){
        this.nom = pra.nom;
        this.prenom = pra.prenom;
        this.numero = pra.numero;
        this.coefNotoriete = pra.coefNotoriete;
        this.ville = pra.ville;
    }
}
