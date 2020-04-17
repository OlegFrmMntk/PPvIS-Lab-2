package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SearchDeleteGroup {

    private Group group = new Group();

    public SearchDeleteGroup(Controller controller, MainWindowTableGroup tableGroup, String searchOrDelete) {

        Label searchByLabel = new Label(searchOrDelete + " by");
        searchByLabel.setLayoutY(20);
        searchByLabel.setLayoutX(100);

        TextField firstSearchParameterInput = new TextField();
        firstSearchParameterInput.setLayoutX(110);
        firstSearchParameterInput.setLayoutY(70);

        TextField secondSearchParameterInput = new TextField();
        secondSearchParameterInput.setLayoutX(110);
        secondSearchParameterInput.setLayoutY(100);

        ObservableList<String> chooseSearchParametersValues = FXCollections.observableArrayList(
                "Full name and Address", "Birth date", "Receipt date or Full name of Doctor"
        );

        ComboBox<String> chooseSearchParameters = new ComboBox<>(chooseSearchParametersValues);
        chooseSearchParameters.setLayoutY(40);
        chooseSearchParameters.setLayoutX(30);

        group.getChildren().addAll(searchByLabel, chooseSearchParameters,
                firstSearchParameterInput, secondSearchParameterInput);

        chooseSearchParameters.setOnAction(actionEvent -> {
            switch (chooseSearchParameters.getValue()) {
                case "Full name and Address":
                    Button fullNameAndAddressButton = getButton(searchOrDelete);

                    group.getChildren().remove(2, group.getChildren().size());

                    group.getChildren().addAll(firstSearchParameterInput,
                                               secondSearchParameterInput,
                                               getFirstParameterLabel("Full Name"),
                                               getSecondParameterLabel("Address"),
                                               fullNameAndAddressButton);

                    fullNameAndAddressButton.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.fullNameAndAddress(firstSearchParameterInput.getText(),
                                                          secondSearchParameterInput.getText());
                        }
                        else {
                            int deleteNumber = controller.fullNameAndAddressDelete(firstSearchParameterInput.getText(),
                                                                                   secondSearchParameterInput.getText());

                            Stage stage = (Stage) fullNameAndAddressButton.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }
                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });

                    break;
                case "Birth date":

                    Button birthDateButton = getButton(searchOrDelete);
                    DatePicker inputBirthDateForSearch = getDatePickerForSearch();

                    group.getChildren().remove(2, group.getChildren().size());
                    group.getChildren().addAll(inputBirthDateForSearch,
                                               getFirstParameterLabel("Birth date"),
                                               birthDateButton);

                    birthDateButton.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.birthDate(inputBirthDateForSearch.getValue());
                        }
                        else {
                            int deleteNumber = controller.birthDateDelete(inputBirthDateForSearch.getValue());

                            Stage stage = (Stage) birthDateButton.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }

                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });

                    break;

                case "Receipt date or Full name of Doctor":
                    Button receiptDateOrDoctorDullNameButton = getButton(searchOrDelete);
                    DatePicker inputReceiptDateForSearch = getDatePickerForSearch();

                    group.getChildren().remove(2, group.getChildren().size());
                    group.getChildren().addAll(inputReceiptDateForSearch,
                                               secondSearchParameterInput,
                                               getFirstParameterLabel("Receipt date"),
                                               getSecondParameterLabel("Doctor Full name"));

                    receiptDateOrDoctorDullNameButton.setOnAction(event -> {
                        if (searchOrDelete.equals("Search")) {
                            controller.receiptDateOrDoctorFullName(inputReceiptDateForSearch.getValue(),
                                                                   secondSearchParameterInput.getText());
                            tableGroup.updateTable();
                        }
                        else {
                            int deleteNumber = controller.receiptDateOrDoctorFullNameDelete(
                                                                            inputReceiptDateForSearch.getValue(),
                                                                            secondSearchParameterInput.getText());

                            Stage stage = (Stage) receiptDateOrDoctorDullNameButton.getScene().getWindow();
                            stage.close();
                            showDeleteInformation(deleteNumber);
                        }

                        tableGroup.setPageNumber(1);
                        tableGroup.updateTable();
                    });

                    break;
            }
        });
    }

    private Label getFirstParameterLabel(String parameterName) {

        Label label = new Label(parameterName);
        label.setLayoutY(70);

        return label;
    }

    private Label getSecondParameterLabel(String parameterName) {

        Label label = new Label(parameterName);
        label.setLayoutY(100);

        return label;
    }

    private DatePicker getDatePickerForSearch() {

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(2001, 7, 14));
        datePicker.setLayoutX(110);
        datePicker.setLayoutY(70);

        return datePicker;
    }

    private Button getButton(String buttonText) {

        Button button = new Button(buttonText);
        button.setLayoutY(130);
        button.setLayoutX(100);

        return button;
    }

    public Group getGroup() {
        return group;
    }

    private void showDeleteInformation(int numOfDeleted) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Information");

        if (numOfDeleted != 0) {
            alert.setContentText(Integer.toString(numOfDeleted) + " record(s) have been deleted");
        } else {
            alert.setContentText("No records found!");
        }

        alert.showAndWait();
    }
}
