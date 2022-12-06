package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {

    public static Visiteur seConnecter(String matricule ,String mdp ) throws ConnexionException{


        Connection connexion = ConnexionBD.getConnexion() ;

        String requeteConnection
                 = "select Visiteur.vis_matricule, vis_nom, vis_prenom "
                + "from Visiteur "
                + "inner join Travailler "
                + "on Visiteur.vis_matricule = Travailler.vis_matricule "
                + "where tra_role LIKE 'Délégué' "
                + "and jjmmaa =  (SELECT max(jjmmaa) FROM Travailler where tra_role = 'Délégué' ) "
                + "and Visiteur.vis_matricule LIKE ? "
                + "and vis_mdp = ?" ;
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
    public static ObservableList<Praticiens> getPraticiens() throws ConnexionException {

        ObservableList<Praticiens> praticiens = FXCollections.observableArrayList();

        Connection connexion = ConnexionBD.getConnexion() ;

        String requeteGetPraticiensEtRapport
        = "SELECT P.pra_num, P.pra_nom, P.pra_prenom, P.pra_ville, P.pra_coefnotoriete, "
        + "RV.rap_date_visite, RV.rap_coef_confiance "
        + "FROM Praticien P "
        + "JOIN RapportVisite RV on P.pra_num = RV.pra_num "
        + "WHERE RV.rap_date_visite = ( "
        + "SELECT max(RV2.rap_date_visite) "
        + "FROM RapportVisite RV2 "
        + "JOIN RapportVisite rv on P.pra_num = RV2.pra_num) ";

        try {
            PreparedStatement requetePreparee = connexion.prepareStatement(requeteGetPraticiensEtRapport);
            ResultSet resultat = requetePreparee.executeQuery();
            while(resultat.next()){
                LocalDate dateDerniereVisite = LocalDate.parse(resultat.getString("rap_date_visite"));
                praticiens.add(new Praticiens(
                                    resultat.getInt("pra_num"),
                                    resultat.getString("pra_nom"),
                                    resultat.getString("pra_prenom"),
                                    resultat.getString("pra_ville"),
                                    resultat.getDouble("pra_coefnotoriete"),
                                    dateDerniereVisite,
                                    resultat.getInt("rap_coef_confiance")
                                ));
            }
            return praticiens;
        }catch(Exception e){
                System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());
            }
        return null;
    }
}



/* SE CONNECTER DELEGUE

        = "SELECT V.vis_matricule, V.vis_nom, V.vis_prenom, T.tra_role, T.jjmmaa "
                + "FROM Visiteur V "
                + "JOIN Travailler T on V.vis_matricule = T.vis_matricule "
                + "WHERE T.jjmmaa = ( "
                + "      SELECT max(jjmmaa) "
                + "        FROM Travailler t "
                + "        JOIN Visiteur v on t.vis_matricule = v.vis_matricule "
                + "        WHERE v.vis_matricule = ? "
                + "        AND v.vis_mdp =  ? ) "
                + " AND T.tra_role = 'Délégué' ";

 */