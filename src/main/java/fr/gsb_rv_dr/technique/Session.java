package fr.gsb_rv_dr.technique;


import fr.gsb_rv_dr.entites.Visiteur;

public class Session {
   static private Session session = null;
   private static Visiteur leVisiteur ;

    private Session(Visiteur leVisiteur) {
        Session.leVisiteur = leVisiteur;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Session.session = session;
    }

    public static Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        Session.leVisiteur = leVisiteur;
    }

    static public void ouvrir(Visiteur visiteur){
        Session.session = new Session(visiteur);
    }

    static public void fermer(){
        Session.session = null;
    }

    static public boolean estOuverte(){
        return Session.session != null;
    }

    @Override
    public String toString() {
        return "Session{" +
                "leVisiteur=" + leVisiteur +
                "session=" + session +
                '}';
    }
}
