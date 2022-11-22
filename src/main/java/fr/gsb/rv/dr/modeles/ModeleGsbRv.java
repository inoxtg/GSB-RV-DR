package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModeleGsbRv {

    public static Visiteur seConnecter(String matricule ,String mdp ) throws ConnexionException{


        Connection connexion = ConnexionBD.getConnexion() ;

        String requeteConnection
                ="SELECT * "
                +"FROM Visiteur "
                +"WHERE vis_matricule = ? "
                +"AND vis_mdp = ? ";
        try {
            PreparedStatement requetePreparee = connexion.prepareStatement( requeteConnection ) ;
            requetePreparee.setString( 1 , matricule);
            requetePreparee.setString(2, mdp);
            ResultSet resultat = requetePreparee.executeQuery();
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom(resultat.getString("vis_prenom"));

                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch(Exception e){
            System.out.println("BALISE ERREUR REQUETE PREP");
            return null ;
        }
    }

}
