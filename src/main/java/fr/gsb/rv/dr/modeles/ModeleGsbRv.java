package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
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
                = "SELECT P.pra_num, P.pra_nom, P.pra_prenom, P.pra_ville, P.pra_coefnotoriete, P.pra_adresse, P.pra_cp, "
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
                                    resultat.getInt("rap_coef_confiance"),
                                    resultat.getString("pra_adresse"),
                                    resultat.getString("pra_cp")
                                ));
            }
            return praticiens;
        }catch(Exception e){

                System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());
            }
        return null;
    }
    public static List<Visiteur> getVisiteurs() throws ConnexionException {

        List<Visiteur> visis = new ArrayList<>();

        Connection connexion = ConnexionBD.getConnexion() ;

        String req
                = "SELECT vis_matricule, vis_nom, vis_prenom "
                + "FROM Visiteur ";

        try{
            PreparedStatement requetePreparee = connexion.prepareStatement(req);
            ResultSet resultat = requetePreparee.executeQuery();
            while(resultat.next()){
                visis.add(new Visiteur(
                   resultat.getString("vis_matricule"),
                   resultat.getString("vis_nom"),
                   resultat.getString("vis_prenom")
                ));
            }
            return visis;
        }catch(Exception e){

            System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());

        }

        return null;
    }
    public static List<RapportVisite> getRapportsVisites(String visMatricule, int mois, int annee) throws ConnexionException{

        List<RapportVisite> rapVis = new ArrayList<>();

        Connection connexion = ConnexionBD.getConnexion() ;

        String req
                = "SELECT "
                + "V.vis_matricule, V.vis_nom, V.vis_prenom, "
                + "RV.rap_num, RV.rap_date_visite, RV.rap_date_redaction, RV.rap_bilan, RV.rap_autre_modif, RV.rap_coef_confiance, "
                + "P.pra_num, P.pra_prenom, P.pra_nom, P.pra_ville, P.pra_coefnotoriete, P.pra_adresse, P.pra_cp "
                    + "FROM RapportVisite RV "
                    + "JOIN Visiteur V on RV.vis_matricule = V.vis_matricule "
                    + "JOIN Praticien P on RV.pra_num = P.pra_num "
                        + "WHERE RV.vis_matricule LIKE ? "
                        + "AND DATE_FORMAT(RV.rap_date_visite, '%c') LIKE ? "
                        + "AND DATE_FORMAT(RV.rap_date_visite, '%Y') LIKE ? "
                        + "AND RV.rap_date_visite = ( "
                            + "SELECT max(RV2.rap_date_visite) "
                            + "FROM RapportVisite RV2 "
                            + "JOIN RapportVisite rv on P.pra_num = RV2.pra_num) ";

        try{

            PreparedStatement requetePreparee = connexion.prepareStatement(req) ;
            requetePreparee.setString( 1 , visMatricule);
            requetePreparee.setInt(2, mois);
            requetePreparee.setInt(3, annee);
            ResultSet resultat = requetePreparee.executeQuery();

            while(resultat.next()){

                LocalDate dateDerniereVisite = LocalDate.parse(resultat.getString("rap_date_visite"));
                LocalDate dateRedaction = LocalDate.parse(resultat.getString("rap_date_redaction"));

                Praticiens pra = new Praticiens(
                        resultat.getInt("pra_num"),
                        resultat.getString("pra_nom"),
                        resultat.getString("pra_prenom"),
                        resultat.getString("pra_ville"),
                        resultat.getDouble("pra_coefnotoriete"),
                        dateDerniereVisite,
                        resultat.getInt("rap_coef_confiance"),
                        resultat.getString("pra_adresse"),
                        resultat.getString("pra_cp")
                );

                Visiteur vis = new Visiteur(
                        resultat.getString("vis_matricule"),
                        resultat.getString("vis_nom"),
                        resultat.getString("vis_prenom")
                );

                RapportVisite rap = new RapportVisite(
                        vis,
                        pra,
                        resultat.getInt("rap_num"),
                        dateDerniereVisite,
                        dateRedaction,
                        resultat.getString("rap_bilan"),
                        resultat.getString("rap_autre_modif"),
                        resultat.getInt("rap_coef_confiance"),
                        false
                );

                rapVis.add(rap);
            }
            return rapVis;
        }catch(Exception e){

            System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());

        }
        return null;
    }
    public static boolean setRapportVisiteLu(String visMatricule, int rapNum) throws ConnexionException{

        Connection connexion = ConnexionBD.getConnexion() ;

        String req
                = "UPDATE RapportVisite RV "
                + "SET RV.rap_lu = true "
                + "WHERE RV.vis_matricule = ? "
                + "AND RV.rap_num = ? ";
        try{

            PreparedStatement requetePreparee = connexion.prepareStatement(req) ;
            requetePreparee.setString( 1 , visMatricule);
            requetePreparee.setInt(2, rapNum);
            int nbLignes = requetePreparee.executeUpdate();

            if(nbLignes != 0){
                return true;
            }

        }catch(Exception e){

            System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());

        }
        return false;
    }
}
