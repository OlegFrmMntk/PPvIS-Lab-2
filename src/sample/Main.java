package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.File;

public class Main extends Application {

    ObservableList<Patient> patientsData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(patientsData);

        Button open = new Button("Open");
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        Button save = new Button("Save");
        Button search = new Button("Search");
        Button exit = new Button("Exit");

        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(15,20,10,10));
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(open, add, search, delete, save, exit);

        new Table(new TableView());
        Table tableView = new Table(new TableView());

        add.setOnAction(event -> new AddInfo().add(patientsData, tableView));

        open.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            controller.open(file, patientsData);
            tableView.updateAll(patientsData);
        });

        save.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
            controller.save(file,patientsData);
        });

        search.setOnAction(event -> new SearchInfo().run(patientsData));
        delete.setOnAction(event -> new DeleteInfo().run(patientsData, tableView));
        exit.setOnAction(event -> controller.exit());

        VBox patientsTable = tableView.create(patientsData);
        VBox general = new VBox(buttons, patientsTable);

        primaryStage.setTitle("Patients");
        primaryStage.setScene(new Scene(general, 700, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}