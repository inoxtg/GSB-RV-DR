package fr.gsb.rv.dr.test;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import org.controlsfx.control.PropertySheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class TestBDD {

    public static void main(String[] args) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;

        String url = "jdbc:mariadb://localhost:3306/gsbrv";
        String user = "theophile";
        String pssd = "781227";

        try {
            System.out.println("TRY 2");
            connection = DriverManager.getConnection(url, user, pssd);
        } catch (SQLException e) {
            System.out.println("CATCH 2" + e.getErrorCode());

            System.exit(-2);
        }
        System.out.println("Successfully connected");

        try {
            connection.close();
            System.out.println("TRY 3");
        } catch (SQLException e) {
            System.out.println("CATCH 3");

            System.exit(-3);
        }
        try {
            System.out.println("TRY 4.1 " + "\n");
            List<Praticiens> pras = new ArrayList<>();
            for (Praticiens pra : ModeleGsbRv.getPraticiens()) {
                pras.add(pra);
                System.out.println(pra.toString());
            }
            System.out.println("TRY 4.2 " + "\n");
            Collections.sort(pras, new ComparateurCoefConfiance());
            for (Praticiens pra : pras) {
                System.out.println(pra.toString());
            }
            System.out.println("TRY 4.3 " + "\n");
            Collections.sort(pras, new ComparateurCoefNotoriete());
            for (Praticiens pra : pras) {
                System.out.println(pra.toString());
            }
            System.out.println("TRY 4.4 " + "\n");
            Collections.sort(pras, new ComparateurDateVisite());
            for (Praticiens pra : pras) {
                System.out.println(pra.toString());
            }
        } catch (Exception e) {
            System.out.println("CATCH 4 " + e.getMessage());
        }
        try {
            System.out.println("TRY 5 " + "\n");
            List<RapportVisite> rap = new ArrayList<>();
            for (RapportVisite r : ModeleGsbRv.getRapportsVisites("b4",11,2022)) {
                rap.add(r);
                System.out.println("\n" + "\n" + "--------------RAPPORTS--------------" + "\n" + "\n");
                System.out.println(r.toString() + "\n");
                System.out.println("\n" + "\n" + "--------------PRATICIENS--------------" + "\n" + "\n");
                System.out.println(r.getLePraticien().toString() + "\n");
                System.out.println("\n" + "\n" + "--------------VISITEURS--------------" + "\n" + "\n");
                System.out.println(r.getLeVisiteur().toString() + "\n");
            }

        } catch (Exception e) {
            System.out.println("CATCH 5 " + e.getMessage());
        }
        try{
            System.out.println("TRY 6 " + "\n");
            boolean o = ModeleGsbRv.setRapportVisiteLu("b4",1);
            System.out.println(o);
        } catch (Exception e) {
            System.out.println("CATCH 6 " + e.getMessage());
        }
    }
}
