package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;
public class Session {

    private static Session session = null;
    private Visiteur leVisiteur;

    private Session(Visiteur leVisiteur){

        this.leVisiteur = leVisiteur;
    }
    public static void ouvrir(Visiteur leVisiteur){

        Session.session = new Session(leVisiteur);
    }
    public static void fermer(){

        Session.session = null;
    }

    public static Session getSession() {

        return Session.session;
    }

    public Visiteur getLeVisiteur() {

        return leVisiteur;
    }

    public static boolean estOuverte(){
        if(Session.session != null){
            return true;
        }
        return false;
    }
}
