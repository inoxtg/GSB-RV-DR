package fr.gsb.rv.dr.component;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class Modal {
    public static boolean confirmation(String title, String headerText, String contentText) {

        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
        alertQuitter.setTitle(title);
        alertQuitter.setHeaderText(headerText);
        alertQuitter.setContentText(contentText);

        alertQuitter.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> response = alertQuitter.showAndWait();

        if(!response.isPresent()){
            return false;
        }else if(response.get() == ButtonType.OK){
            return true;
        }else if(response.get() == ButtonType.CANCEL){
            return false;
        }
        return false;
    }
    public static Optional<Pair<String, String>> login(){

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Saisissez vos identifiants de connexion");


        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Matricule");

        PasswordField password = new PasswordField();
        password.setPromptText("Mot de passe");


        grid.add(new Label("Matricule :"), 0, 0);
        grid.add(username, 1, 0);

        grid.add(new Label("Mot de passe :"), 0, 1);
        grid.add(password, 1, 1);


        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        return result;
    }
}
