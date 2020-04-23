package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class MainWindowTableGroup {

    private Group group = new Group();
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private Controller controller;
    private Label pageCountLabel = new Label();
    private Label totalRecordsCountLabel = new Label();
    private Label pageNumberLabel = new Label();
    private int totalRecordsCount = 0;
    private int recordsOnPageCount = 10;
    private int pageCount = 1;
    private int pageNumber = 1;
    private Page<Patient> currentPage;

    public MainWindowTableGroup(Controller controller) {

        this.controller = controller;

        Label chooseRecordsOnPageCountLabel = new Label("Choose record count");
        chooseRecordsOnPageCountLabel.setLayoutY(385);

        ObservableList<Integer> recordsCountValues = FXCollections.observableArrayList(5, 10, 15);

        ComboBox<Integer> chooseRecordsOnPageCountValueCombo = new ComboBox<>(recordsCountValues);

        chooseRecordsOnPageCountValueCombo.setValue(10);
        chooseRecordsOnPageCountValueCombo.setLayoutY(385);
        chooseRecordsOnPageCountValueCombo.setLayoutX(120);

        chooseRecordsOnPageCountValueCombo.setOnAction(actionEvent -> {
            recordsOnPageCount = chooseRecordsOnPageCountValueCombo.getValue();
            pageNumber = 1;
            updateTable();
        });

        TableView<Patient> table = new TableView<>(patients);

        TableColumn<Patient, String> fullNameColumn = new TableColumn<Patient, String>("Full name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("fullName"));
        fullNameColumn.setPrefWidth(120);
        table.getColumns().add(fullNameColumn);

        TableColumn<Patient, String> addressColumn = new TableColumn<Patient, String>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        addressColumn.setPrefWidth(150);
        table.getColumns().add(addressColumn);

        TableColumn<Patient, LocalDate> birthDateColumn = new TableColumn<Patient, LocalDate>("Birth date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Patient, LocalDate>("birthDate"));
        birthDateColumn.setPrefWidth(80);
        table.getColumns().add(birthDateColumn);

        TableColumn<Patient, LocalDate> receiptDateColumn = new TableColumn<Patient, LocalDate>("Receipt date");
        receiptDateColumn.setCellValueFactory(new PropertyValueFactory<Patient, LocalDate>("receiptDate"));
        receiptDateColumn.setPrefWidth(80);
        table.getColumns().add(receiptDateColumn);

        TableColumn<Patient, String> doctorFullNameColumn = new TableColumn<Patient, String>("Doctor name");
        doctorFullNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("doctorFullName"));
        doctorFullNameColumn.setPrefWidth(120);
        table.getColumns().add(doctorFullNameColumn);

        TableColumn<Patient, String> conclusionColumn = new TableColumn<Patient, String>("Conclusion");
        conclusionColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("conclusion"));
        conclusionColumn.setPrefWidth(198);
        table.getColumns().add(conclusionColumn);

        table.setPrefWidth(750);
        table.setPrefHeight(385);

        pageCountLabel.setText("Page count:" + pageCount);
        pageCountLabel.setLayoutY(420);

        totalRecordsCountLabel.setText("Total records count:" + totalRecordsCount);
        totalRecordsCountLabel.setLayoutY(450);

        pageNumberLabel.setText("Page number:" + pageNumber);
        pageNumberLabel.setLayoutY(460);
        pageNumberLabel.setLayoutX(295);

        Button firstPageButton = new Button("First page");
        firstPageButton.setOnAction(actionEvent -> {
            pageNumber = 1;
            updateTable();
        });
        firstPageButton.setLayoutY(420);
        firstPageButton.setLayoutX(220);

        Button nextPageButton = new Button("->");
        nextPageButton.setOnAction(actionEvent -> {
            if (pageNumber != pageCount) {
                pageNumber += 1;
                updateTable();
            }
        });
        nextPageButton.setLayoutY(420);
        nextPageButton.setLayoutX(340);

        Button previousPageButton = new Button("<-");
        previousPageButton.setOnAction(actionEvent -> {
            if (pageNumber != 1) {
                pageNumber -= 1;
                updateTable();
            }
        });
        previousPageButton.setLayoutY(420);
        previousPageButton.setLayoutX(300);

        Button lastPageButton = new Button("Last page");
        lastPageButton.setOnAction(actionEvent -> {
            pageNumber = pageCount;
            updateTable();
        });
        lastPageButton.setLayoutY(420);
        lastPageButton.setLayoutX(380);

        group.getChildren().addAll(table, chooseRecordsOnPageCountLabel, chooseRecordsOnPageCountValueCombo,
                                   pageCountLabel, totalRecordsCountLabel, firstPageButton,
                                   previousPageButton, nextPageButton, lastPageButton, pageNumberLabel);

    }

    public void updateTable() {
        updateCurrentPage();
        patients.setAll(currentPage.getPatients());
        pageCount = currentPage.getPageCount();
        pageCountLabel.setText("Page count:" + getPageCount());
        totalRecordsCount = currentPage.getTotalRecordsCount();
        totalRecordsCountLabel.setText("Total records count:" + getTotalRecordsCount());
        pageNumberLabel.setText("Page number:" + currentPage.getPageNumber());
    }

    public void updateCurrentPage() {
        setCurrentPage(controller.updateMainWindowTableView(pageNumber, recordsOnPageCount));
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getRecordsOnPageCount() {
        return recordsOnPageCount;
    }

    public Group getGroup() {
        return group;
    }

    public ObservableList<Patient> getPatients() {
        return this.patients;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setCurrentPage(Page<Patient> currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
