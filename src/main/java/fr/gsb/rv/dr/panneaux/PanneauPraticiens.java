package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;

public class PanneauPraticiens extends Pane {

    public static void show(BorderPane panel) {

        Text tableName = new Text("Praticiens Hesitants");
        tableName.setTextAlignment(TextAlignment.CENTER);
        TableView<Praticiens> tablePraticiens = new TableView<Praticiens>();
        tablePraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablePraticiens.setBackground(Background.EMPTY);

        ObservableList<Praticiens> data;
        try {
            data = ModeleGsbRv.getPraticiens();
            for(Praticiens d : data){
                System.out.println(d.toString() + "\n");
            }
        } catch (ConnexionException e) {
            throw new RuntimeException(e);
        }
        tablePraticiens.setEditable(true);

        TableColumn<Praticiens, Integer> numPra = new TableColumn<Praticiens, Integer>("Numero");
        numPra.setCellValueFactory(
                new PropertyValueFactory<Praticiens, Integer>("numero")
        );
        TableColumn<Praticiens, String> nomPra = new TableColumn<Praticiens, String>("Nom");
        nomPra.setCellValueFactory(
                new PropertyValueFactory<Praticiens, String>("nom")
        );
        TableColumn<Praticiens, String> prenPra = new TableColumn<Praticiens, String>("Prenom");
        prenPra.setCellValueFactory(
                new PropertyValueFactory<Praticiens, String>("prenom")
        );
        TableColumn<Praticiens, String> villepra = new TableColumn<Praticiens, String>("Ville");
        villepra.setCellValueFactory(
                new PropertyValueFactory<Praticiens, String>("ville")
        );
        TableColumn<Praticiens, Double> coefNotoriete = new TableColumn<Praticiens, Double>("Coefficient de Norotiété");
        coefNotoriete.setCellValueFactory(
                new PropertyValueFactory<Praticiens, Double>("coefNotoriete")
        );
        TableColumn<Praticiens, LocalDate> dateDerniereVisite = new TableColumn<Praticiens, LocalDate>("Date de dernière visite");
        dateDerniereVisite.setCellValueFactory(
                new PropertyValueFactory<Praticiens, LocalDate>("dateDerniereVisite")
        );
        TableColumn<Praticiens, Integer> dernierCoefConfiance = new TableColumn<Praticiens, Integer>("Dernier coefficient de Confiance ");
        dernierCoefConfiance.setCellValueFactory(
                new PropertyValueFactory<Praticiens, Integer>("dernierCoefConfiance")
        );
        tablePraticiens.getColumns().addAll(numPra, nomPra, prenPra, villepra, coefNotoriete, dateDerniereVisite, dernierCoefConfiance);
        tablePraticiens.setEditable(false);
        tablePraticiens.setItems(data);

        VBox v = new VBox(tablePraticiens);
        panel.setTop(tableName);
        panel.setCenter(v);

    }

}
