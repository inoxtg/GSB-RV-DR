package fr.gsb.rv.dr.test;

import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
        try{
            System.out.println(ModeleGsbRv.getPraticiens());
            System.out.println("TRY 4 " );
        }catch(Exception e){
            System.out.println("CATCH 4 " + e.getMessage());
        }
    }
}
