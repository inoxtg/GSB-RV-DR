package fr.gsb.rv.dr.utilitaires;
import fr.gsb.rv.dr.entites.Praticiens;
import java.util.Comparator;

public class ComparateurCoefConfiance implements Comparator<Praticiens> {

    @Override
    public int compare(Praticiens pra1, Praticiens pra2) {

        if(pra1.getDernierCoefConfiance() > pra2.getDernierCoefConfiance()){
            return 1;
        }
        else if (pra1.getDernierCoefConfiance() == pra2.getDernierCoefConfiance()) {
            return 0;
        }else {
            return -1;
        }
    }
}

