package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PanneauPraticiens extends Pane {

    public static void show(BorderPane panel){

        TableView tablePraticiens = new TableView();
        tablePraticiens.setEditable(true);

        TableColumn numPra = new TableColumn("Numero : ");
        TableColumn nomPra = new TableColumn("Nom : ");
        TableColumn prenPra = new TableColumn("Prenom : ");
        TableColumn villepra = new TableColumn("Ville :");
        TableColumn coefNotoriete = new TableColumn("Coefficient de Norotiété : ");

        tablePraticiens.getColumns().addAll(numPra, nomPra, prenPra, villepra, coefNotoriete);

        try {
            ArrayList<Praticiens> praticiens = ModeleGsbRv.getPraticiens();
            for(Praticiens pra : praticiens){
                numPra.getColumns().add(1,pra.getNumero());
            }
        } catch (ConnexionException e) {
            throw new RuntimeException(e);
        }

        panel.setCenter(tablePraticiens);

    }
}
