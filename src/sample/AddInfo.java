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

import java.time.LocalDate;

public class AddInfo {
    public void add(ObservableList<Patient> patients, Table table){
        Stage adding = new Stage();

        VBox addingStage = new VBox();
        addingStage.setPadding(new Insets(10, 20, 20, 20));
        addingStage.setSpacing(10);
        
        VBox dateBox = new VBox();
        dateBox.setPadding(new Insets(10, 20, 20, 20));
        dateBox.setSpacing(10);

        Label name = new Label("Name");
        TextField nameInput = new TextField();
        nameInput.setMinWidth(300);
        addingStage.getChildren().addAll(name, nameInput);

        Label surname = new Label("Surname");
        TextField surnameInput = new TextField();
        surnameInput.setMinWidth(300);
        addingStage.getChildren().addAll(surname, surnameInput);

        Label address = new Label("Address");
        TextField addressInput = new TextField();
        addressInput.setMinWidth(300);
        addingStage.getChildren().addAll(address, addressInput);

        Label birthDate = new Label("Date of Birth");
        DatePicker birthDateInput = new DatePicker(LocalDate.of(1900, 1, 1));
        dateBox.getChildren().addAll(birthDate, birthDateInput);

        Label receiptDate = new Label("Date of receipt");
        DatePicker receiptDateInput = new DatePicker(LocalDate.of(2020, 4, 1));
        dateBox.getChildren().addAll(receiptDate, receiptDateInput);

        Label doctorName = new Label("Doctor name");
        TextField doctorNameInput = new TextField();
        doctorNameInput.setMinWidth(300);
        addingStage.getChildren().addAll(doctorName, doctorNameInput);

        Label doctorSurname = new Label("Doctor surname");
        TextField doctorSurnameInput = new TextField();
        doctorSurnameInput.setMinWidth(300);
        addingStage.getChildren().addAll(doctorSurname, doctorSurnameInput);

        Label conclusion = new Label("Conclusion");
        TextField conclusionInput = new TextField();
        conclusionInput.setMinWidth(300);
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

                Patient patientData = new Patient(nameInput.getText(), surnameInput.getText(), addressInput.getText(),
                                                  birthDateInput.getValue(), receiptDateInput.getValue(),
                                                  doctorNameInput.getText(), doctorSurnameInput.getText(),
                                                  conclusionInput.getText());

                Controller controller = new Controller(patients);
                controller.add(patientData);
                table.updateAll(patients);
                adding.close();
            }
        });

        adding.setTitle("Information");
        adding.setScene(new Scene(new HBox(addingStage, dateBox), 600, 450));
        adding.show();
    }
}
