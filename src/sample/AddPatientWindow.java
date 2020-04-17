package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddPatientWindow {

    private Controller controller;
    private MainWindowTableGroup tableGroup;

    public AddPatientWindow(MainWindowTableGroup tableGroup, Stage primaryStage, Controller controller) {

        this.controller = controller;
        this.tableGroup = tableGroup;

        HBox layout = getWindowLayout();

        Scene secondScene = new Scene(layout);
        Stage newWindow = new Stage();
        newWindow.setTitle("Information");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(primaryStage);

        newWindow.show();
    }

    public HBox getWindowLayout() {

        VBox addingStage = new VBox();
        addingStage.setPadding(new Insets(10, 20, 20, 20));
        addingStage.setSpacing(10);

        VBox dateBox = new VBox();
        dateBox.setPadding(new Insets(10, 20, 20, 20));
        dateBox.setSpacing(10);

        Label fullName = new Label("Full Name");
        TextField fullNameInput = new TextField();
        fullNameInput.setMinWidth(300);
        addingStage.getChildren().addAll(fullName, fullNameInput);

        Label address = new Label("Address");
        TextField addressInput = new TextField();
        addressInput.setMinWidth(300);
        addingStage.getChildren().addAll(address, addressInput);

        Label birthDate = new Label("Date of Birth");
        DatePicker birthDateInput = new DatePicker(LocalDate.of(2000, 1, 1));
        dateBox.getChildren().addAll(birthDate, birthDateInput);

        Label receiptDate = new Label("Date of receipt");
        DatePicker receiptDateInput = new DatePicker(LocalDate.of(2020, 4, 1));
        dateBox.getChildren().addAll(receiptDate, receiptDateInput);

        Label doctorFullName = new Label("Doctor name");
        TextField doctorFullNameInput = new TextField();
        doctorFullNameInput.setMinWidth(300);
        addingStage.getChildren().addAll(doctorFullName, doctorFullNameInput);

        Label conclusion = new Label("Conclusion");
        TextField conclusionInput = new TextField();
        conclusionInput.setMinWidth(300);
        addingStage.getChildren().addAll(conclusion, conclusionInput);

        Button addInfo = new Button("Add");
        addingStage.getChildren().add(addInfo);

        HBox adding = new HBox(addingStage, dateBox);

        addInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (fullNameInput.getText().isEmpty() || addressInput.getText().isEmpty() ||
                    doctorFullNameInput.getText().isEmpty() || conclusionInput.getText().isEmpty()) {

                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Warning");
                    warning.setContentText("Some fields are empty.");
                    warning.showAndWait();
                    return;
                }

                controller.addPatientToArray(fullNameInput.getText(), addressInput.getText(),
                                             birthDateInput.getValue(), receiptDateInput.getValue(),
                                             doctorFullNameInput.getText(), conclusionInput.getText());

                tableGroup.updateTable();
                Stage stage = (Stage) addInfo.getScene().getWindow();
                stage.close();
            }
        });

        return adding;
    }
}
