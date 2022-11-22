package fr.gsb.rv.dr;

import fr.gsb.rv.dr.component.Modal;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.panneaux.PanneauAccueil;
import fr.gsb.rv.dr.panneaux.PanneauPraticiens;
import fr.gsb.rv.dr.panneaux.PanneauRapports;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Objects;
import java.util.Optional;

public class AppGSB extends Application {

    private PanneauAccueil vueAccueil;
    private PanneauRapports vueRapports;
    private PanneauPraticiens vuePraticiens;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Visiteur visiteur = new Visiteur();
        MenuBar barreMenus = new MenuBar();
        BorderPane root = new BorderPane();

        PanneauAccueil.show(root);
        root.setTop(barreMenus);

        Scene scene = new Scene(root,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

            Menu menuFichier = new Menu("Fichier");
                MenuItem itemFichierSeConnecter = new MenuItem("Se connecter");
                menuFichier.getItems().add(itemFichierSeConnecter);
            MenuItem itemFichierSeDeconnecter = new MenuItem("Se deconnecter");
                menuFichier.getItems().add(itemFichierSeDeconnecter);
            MenuItem itemFichierQuitter = new MenuItem("Quitter");
                menuFichier.getItems().add(itemFichierQuitter);

            Menu menuRapports = new Menu("Rapports");
                MenuItem itemRapportsConsulter = new MenuItem("Consulter");
                menuRapports.getItems().add(itemRapportsConsulter);

            Menu menuPraticiens = new Menu("Praticiens");
                MenuItem itemPraticiensHesitants = new MenuItem("Hésitants");
                menuPraticiens.getItems().add(itemPraticiensHesitants);

        menuPraticiens.setDisable(true);
        menuRapports.setDisable(true);
        itemFichierSeDeconnecter.setDisable(true);

        primaryStage.setTitle("GSB-RV-DR");

        itemPraticiensHesitants.setOnAction(actionEvent ->{
            System.out.println("[RAPPORTS]" + " " + visiteur.getNom() + " " + visiteur.getPrenom());
        });
        itemRapportsConsulter.setOnAction(actionEvent ->{
            System.out.println("[PRATICIENS]" + " " + visiteur.getNom() + " " + visiteur.getPrenom());
        });

        itemFichierSeConnecter.setOnAction(actionEvent -> {
            Optional<Pair<String, String>> resultat = Modal.login();
            String matricule = resultat.get().getKey();
            String mdp = resultat.get().getValue();
            if (!mdp.isEmpty() | !matricule.isEmpty()) {
                try {
                    if(ModeleGsbRv.seConnecter(matricule, mdp) != null) {
                        visiteur.set(Objects.requireNonNull(ModeleGsbRv.seConnecter(matricule, mdp)));
                        menuPraticiens.setDisable(false);
                        menuRapports.setDisable(false);
                        itemFichierSeConnecter.setDisable(true);
                        itemFichierSeDeconnecter.setDisable(false);
                        Session.ouvrir(visiteur);
                        primaryStage.setTitle("GSB-RV-DR" + " " + visiteur.getNom() + " " + visiteur.getPrenom());
                        root.setCenter(null);
                    }else{
                        Text t = new Text("ERREUR MOT DE PASSE OU NOM DUTILISATEUR INCORRECT");
                        root.setCenter(t);
                    }
                } catch (ConnexionException e) {
                    throw new RuntimeException();
                }
            }else{
                Text t = new Text("ERREUR MOT DE PASSE VIDE");
                root.setCenter(t);
            }
        });

        itemFichierSeDeconnecter.setOnAction(actionEvent -> {
            menuPraticiens.setDisable(true);
            menuRapports.setDisable(true);
            itemFichierSeDeconnecter.setDisable(true);
            itemFichierSeConnecter.setDisable(false);
            Session.fermer();
            primaryStage.setTitle("GSB-RV-DR");
        });

        barreMenus.getMenus().add(menuFichier);
        barreMenus.getMenus().add(menuPraticiens);
        barreMenus.getMenus().add(menuRapports);


        itemFichierQuitter.setOnAction(actionEvent ->{
          boolean reponse = Modal.confirmation("Quitter","Demande de confirmation", "Voulez-vous vraiment quitter l'application ?");
          if(reponse){
              Platform.exit();
          }
        });

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {

        launch();
    }
}