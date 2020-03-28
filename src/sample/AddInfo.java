package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddInfo {
    public void add(ObservableList<Patient> patients, Table table){
        Stage adding = new Stage();

        VBox addingStage = new VBox();
        addingStage.setPadding(new Insets(10, 20, 20, 20));
        addingStage.setSpacing(10.d);

        Label name = new Label("Name:      ");
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(300);
        addingStage.getChildren().addAll(name, nameInput);

        Label surname = new Label("Surname: ");
        TextField surnameInput = new TextField();
        surnameInput.setMaxWidth(300);
        addingStage.getChildren().addAll(surname, surnameInput);

        Label address = new Label("Address: ");
        TextField addressInput = new TextField();
        addressInput.setMaxWidth(300);
        addingStage.getChildren().addAll(address, addressInput);

        // DATE

        Label doctorName = new Label("Doctor name:      ");
        TextField doctorNameInput = new TextField();
        doctorNameInput.setMaxWidth(300);
        addingStage.getChildren().addAll(doctorName, doctorNameInput);

        Label doctorSurname = new Label("Doctor surname: ");
        TextField doctorSurnameInput = new TextField();
        doctorSurnameInput.setMaxWidth(300);
        addingStage.getChildren().addAll(doctorSurname, doctorSurnameInput);


        Label conclusion = new Label("Conclusion:");
        TextField conclusionInput = new TextField();
        conclusionInput.setMaxWidth(300);
        addingStage.getChildren().addAll(conclusion, conclusionInput);

        Button addInfo = new Button("Add");
        addingStage.getChildren().add(addInfo);

        addInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameInput.getText().isEmpty() || surnameInput.getText().isEmpty() ||
                    addressInput.getText().isEmpty() || doctorNameInput.getText().isEmpty() ||
                    doctorSurnameInput.getText().isEmpty() || conclusionInput.getText().isEmpty()) {

                    Alert warn = new Alert(Alert.AlertType.WARNING);
                    warn.setTitle("Warning");
                    warn.setContentText("Some fields are empty.");
                    warn.showAndWait();
                    return;
                }

                // Вкинуть дату
                Patient patientData = new Patient(nameInput.getText(), surnameInput.getText(), addressInput.getText(),
                                                 null, null, doctorNameInput.getText(),
                                                  doctorSurnameInput.getText(), conclusionInput.getText());

                Controller controller = new Controller(patients);
                controller.add(patientData);
                table.updateAll(patients);
                adding.close();
            }
        });

        adding.setTitle("Information");
        adding.setScene(new Scene(addingStage, 600, 450));
        adding.show();
    }
}
