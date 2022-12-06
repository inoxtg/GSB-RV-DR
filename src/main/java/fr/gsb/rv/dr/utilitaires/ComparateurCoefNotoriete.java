package fr.gsb.rv.dr.utilitaires;
import fr.gsb.rv.dr.entites.Praticiens;
import java.util.Comparator;

public class ComparateurCoefNotoriete implements Comparator<Praticiens> {

    public int compare(Praticiens pra1, Praticiens pra2) {

        if(pra1.getCoefNotoriete() > pra2.getCoefNotoriete()){
            return 1;
        }
        else if(pra1.getCoefNotoriete() == pra2.getCoefNotoriete()){
            return 0;
        }
        else{
         return -1;
        }
    }
}

