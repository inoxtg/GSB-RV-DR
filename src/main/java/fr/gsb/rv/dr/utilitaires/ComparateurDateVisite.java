package fr.gsb.rv.dr.utilitaires;

import fr.gsb.rv.dr.entites.Praticiens;

import java.util.Comparator;

public class ComparateurDateVisite implements Comparator<Praticiens> {
    @Override
    public int compare(Praticiens pra1, Praticiens pra2) {
        if(pra1.getDateDerniereVisite().isAfter(pra2.getDateDerniereVisite())){
            return 1;
        }
        else if (pra1.getDateDerniereVisite().isEqual(pra2.getDateDerniereVisite())) {
            return 0;
        }
        else{
            return -1;
        }
    }
}
