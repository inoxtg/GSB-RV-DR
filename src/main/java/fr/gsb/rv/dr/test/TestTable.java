package fr.gsb.rv.dr.test;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class TestTable extends Application{

        public void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage outStage) {
// creating table view object
            TableView<Courses> table = new TableView<Courses>();
//obserbale list for taking multiple values at a time
            ObservableList<Courses> courseData = FXCollections.observableArrayList(
                    new Courses("Inheritance"),
                    new Courses("Abstraction"),
                    new Courses("Polymorphism"),
                    new Courses("Generics"),
                    new Courses("OOPS"),
                    new Courses("Functions"));
// Adding columns to the table
            TableColumn javaColumn = new TableColumn("EDUCBA Java");
//adding column value
            javaColumn.setCellValueFactory(new PropertyValueFactory<Courses, String>("javaCorse"));
//setting minimum width
            javaColumn.setMinWidth(100);
// adding columns to the table
            table.getColumns().addAll(javaColumn);
// table.setItems(courseData);
            table.setItems(courseData);
// creating VBox component
            VBox vBox = new VBox();
// adding table to the vBox
            vBox.getChildren().add(table);
// creating a scene for adding vBox
            Scene scene = new Scene(vBox, 400, 400);
// Title for display on top of the application
            outStage.setTitle("Courses List");
// adding scene to the stage
            outStage.setScene(scene);
// displayingoutput
            outStage.show();
        }
        public class Courses {
            private String javaCorse;
            public String getJavaCorse() {
                return javaCorse;
            }
            public void setJavaCorse(String javaCorse) {
                this.javaCorse = javaCorse;
            }
            public Courses(String javaCorse) {
                super();
                this.javaCorse = javaCorse;
            }
        }
    }

