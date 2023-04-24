package fr.gsb_rv_dr.utilitaires;

import fr.gsb_rv_dr.entites.Praticien;
import fr.gsb_rv_dr.entites.Visiteur;

import java.util.Comparator;

public class ComparateurNombreVisites implements Comparator<Visiteur> {

    @Override
    public int compare(Visiteur o1, Visiteur o2) {
        if (o1.getNbVisite().equals(o2.getNbVisite())) {
            return 0;
        } else if (o1.getNbVisite() > o2.getNbVisite()) {
            return 1;
        } else {
            return -1;
        }
    }
}
