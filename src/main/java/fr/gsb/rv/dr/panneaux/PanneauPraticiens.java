package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticiens;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import java.time.LocalDate;
import java.util.Collections;

public class PanneauPraticiens {
    public static void show(BorderPane panel) {
        /*
           RECUPERATION DATA POUR TABLE
        */
        ObservableList<Praticiens> data;
        try {
            data = ModeleGsbRv.getPraticiens();
            assert data != null;
            for(Praticiens d : data){
                System.out.println(d.toString() + "\n");
            }
        } catch (ConnexionException e) {
            throw new RuntimeException(e);
        }
        /*
            TEXTE POUR TOP
        */
        Text textTri = new Text("Selectionner un critère de tri : ");
        textTri.setFont(new Font(15));
        textTri.setWrappingWidth(300);
        textTri.setTextAlignment(TextAlignment.JUSTIFY);
        textTri.setStyle("-fx-font-weight: bold");
        /*
            GROUPE DE BOUTON ET ACTION
        */
        ToggleGroup groupeToggleBtn = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("CoefConfiance");
            rbCoefConfiance.setToggleGroup(groupeToggleBtn);
            rbCoefConfiance.setOnAction(actionEvent -> {
                data.sort(new ComparateurCoefConfiance());
            });
        RadioButton rbCoefNotoriete = new RadioButton("CoefNotoriete");
            rbCoefNotoriete.setToggleGroup(groupeToggleBtn);
            rbCoefNotoriete.setSelected(true);
            rbCoefNotoriete.setOnAction(actionEvent -> {
                data.sort(new ComparateurCoefNotoriete());
                Collections.reverse(data);
            });
        RadioButton rbDateVisite = new RadioButton("DateVisite");
            rbDateVisite.setToggleGroup(groupeToggleBtn);
            rbDateVisite.setOnAction(actionEvent -> {
                data.sort(new ComparateurDateVisite());
                Collections.reverse(data);
            });
        /*
            GRID PANE PLACEMENT DES BOUTONS
        */
        GridPane gpBtn = new GridPane();
        gpBtn.setHgap(10);
        gpBtn.setVgap(10);

        gpBtn.add(rbCoefConfiance, 0, 0);
        gpBtn.add(rbCoefNotoriete, 1, 0);
        gpBtn.add(rbDateVisite, 2, 0);
        /*
            CREATION TABLE
        */
        TableView<Praticiens> tablePraticiens = new TableView<Praticiens>();
        tablePraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablePraticiens.setBackground(Background.EMPTY);
        tablePraticiens.setEditable(true);
        /*
            VBOX (CONTENEUR GLOBAL)
        */
        VBox v = new VBox(textTri, gpBtn, tablePraticiens);
        v.setSpacing(10);
        /*
            CREATION DE COLONNES ET MAPPING DES VALEURS DES VALEURS
        */
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
        /*
            COHERENCE
        */
        tablePraticiens.getColumns().addAll(numPra, nomPra, prenPra, villepra, coefNotoriete, dateDerniereVisite, dernierCoefConfiance);
        tablePraticiens.setItems(data);
        tablePraticiens.setEditable(false);
        /*
            FINISH HIM
        */
        panel.setCenter(v);

    }

}
