package sample;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Patient;

import java.time.LocalDate;

public class Table {

    private Label pageNumberLabel;
    private Label currPageNumLabel;
    private Label sizeLabel;
    private TableView<Patient> table = new TableView<>();
    private int pageIndex;
    private int pageNum;
    private int fromIndex;
    private int toIndex;
    private int rowsPerPage;

    public Table(TableView table) {
        TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Patient, String> surnameColumn = new TableColumn<>("Surname");
        TableColumn<Patient, String> addressColumn = new TableColumn<>("Address");
        TableColumn<Patient, SimpleObjectProperty<LocalDate>> bornDateColumn = new TableColumn<>("Date of born");
        TableColumn<Patient, SimpleObjectProperty<LocalDate>> receiptDateColumn = new TableColumn<>("Date of receipt");
        TableColumn<Patient, String> doctorFioColumn = new TableColumn<>("Doctor FIO");
        TableColumn<Patient, String> conclusionColumn = new TableColumn<>("Conclusion");


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        bornDateColumn.setCellValueFactory(new PropertyValueFactory<>("bornDate"));
        receiptDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptDate"));
        doctorFioColumn.setCellValueFactory(new PropertyValueFactory<>("doctorFio"));
        conclusionColumn.setCellValueFactory(new PropertyValueFactory<>("conclusion"));

        table.getColumns().addAll(nameColumn, surnameColumn, addressColumn, bornDateColumn, receiptDateColumn,
                doctorFioColumn, conclusionColumn);
    }

    public VBox create(ObservableList<Patient> list)  {
        VBox overallBox = new VBox(20);
        overallBox.setAlignment(Pos.CENTER);

        pageIndex = 0;
        rowsPerPage = 10;
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());

        TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Patient, String> surnameColumn = new TableColumn<>("Surname");
        TableColumn<Patient, String> addressColumn = new TableColumn<>("Address");
        TableColumn<Patient, SimpleObjectProperty<LocalDate>> bornDateColumn = new TableColumn<>("Date of born");
        TableColumn<Patient, SimpleObjectProperty<LocalDate>> receiptDateColumn = new TableColumn<>("Date of receipt");
        TableColumn<Patient, String> doctorFioColumn = new TableColumn<>("Doctor FIO");
        TableColumn<Patient, String> conclusionColumn = new TableColumn<>("Conclusion");

        if ((list.size() > (toIndex - fromIndex)) & ((toIndex - fromIndex) != -1)) {
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
        }
        else {
            table.setItems(list);
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        bornDateColumn.setCellValueFactory(new PropertyValueFactory<>("bornDate"));
        receiptDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptDate"));
        doctorFioColumn.setCellValueFactory(new PropertyValueFactory<>("doctorFio"));
        conclusionColumn.setCellValueFactory(new PropertyValueFactory<>("conclusion"));

        table.getColumns().addAll(nameColumn, surnameColumn, addressColumn, bornDateColumn, receiptDateColumn,
                                  doctorFioColumn, conclusionColumn);

        Button prev = new Button("Prev");
        Button next = new Button("Next");
        Button first = new Button("At First");
        Button last = new Button("At Last");
        Button setPage = new Button("Go");
        HBox firstPages = new HBox(10);
        firstPages.getChildren().addAll(first,prev);
        HBox lastPages = new HBox(10);
        lastPages.getChildren().addAll(next,last);

        TextField pageField = new TextField();
        pageField.setMaxWidth(60);

        HBox setPageBox = new HBox(10);
        setPageBox.setAlignment(Pos.CENTER);
        setPageBox.getChildren().addAll(pageField, setPage);
        HBox pageCtrlBox = new HBox(10);
        pageCtrlBox.getChildren().addAll(firstPages,setPageBox,lastPages);
        pageCtrlBox.setAlignment(Pos.CENTER);

        HBox labelsBox = new HBox(10);

        Label sizeLabelInfo = new Label("Quantity of rows");
        Label pageNumInfoLabel = new Label("Pages:");
        sizeLabel = new Label("");
        labelsBox.getChildren().addAll(sizeLabelInfo,sizeLabel);
        labelsBox.setAlignment(Pos.CENTER);
        pageNumberLabel = new Label("");
        HBox pageNumInfo = new HBox(5);
        pageNumInfo.getChildren().addAll(pageNumInfoLabel, pageNumberLabel);
        pageNumInfo.setAlignment(Pos.CENTER);

        Label currPageNumInfoLabel = new Label("Current page");
        currPageNumLabel = new Label("");
        HBox currPageNumInfo = new HBox(5);
        currPageNumInfo.getChildren().addAll(currPageNumInfoLabel, currPageNumLabel);
        currPageNumInfo.setAlignment(Pos.CENTER);

        Button setRowsPerPageButton = new Button("Input number of strings");
        TextField rowsNumPerPage = new TextField();
        HBox rowPerPageSetBox = new HBox(5);
        rowPerPageSetBox.getChildren().addAll(setRowsPerPageButton, rowsNumPerPage);
        rowPerPageSetBox.setAlignment(Pos.CENTER);

        overallBox.getChildren().addAll(table, pageCtrlBox, pageNumInfo, currPageNumInfo,labelsBox, rowPerPageSetBox);


        updatePageNum(list);
        updatePageIndex(0);
        updateData(list);

        prev.setOnAction(event -> {
            if (fromIndex - rowsPerPage >= 0) {
                fromIndex -= rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        next.setOnAction(event -> {
            if (fromIndex + rowsPerPage < list.size()) {
                fromIndex += rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        first.setOnAction(event -> {
            fromIndex = 0;
            toIndex = Math.min(fromIndex + rowsPerPage, list.size());
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
            updatePageIndex(fromIndex);
        });

        last.setOnAction(event -> {
            int tmp = list.size() / rowsPerPage;
            if (list.size() % rowsPerPage == 0) {
                fromIndex = (tmp - 1) * rowsPerPage;
            } else {
                fromIndex = tmp * rowsPerPage;
            }
            toIndex = Math.min(fromIndex + rowsPerPage, list.size());
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
            updatePageIndex(fromIndex);
        });

        setPage.setOnAction(event -> {
            int tmp = Integer.parseInt(pageField.getText()) - 1;
            if (pageNum > tmp) {
                fromIndex = tmp * rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        setRowsPerPageButton.setOnAction(event -> {
            rowsPerPage = Integer.parseInt(rowsNumPerPage.getText());
            updateTable(list);
            updatePageIndex(fromIndex);
            updatePageNum(list);
        });

        return overallBox;
    }

    public void updatePageNum(ObservableList<Patient> list) {
        if (list.size() % rowsPerPage != 0) {
            pageNum = list.size() / rowsPerPage + 1;
        } else {
            pageNum = (list.size() / rowsPerPage);
        }
        pageNumberLabel.setText(String.valueOf(pageNum));
    }

    public void updateTable(ObservableList<Patient> list) {
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
    }
    public void updateData(ObservableList<Patient> list){
        sizeLabel.setText(String.valueOf(list.size()));
    }
    public void updateAll(ObservableList<Patient> list) {
        updatePageNum(list);
        updateTable(list);
        updateData(list);
    }

    void toFirstPage(ObservableList<Patient> list) {
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
        updatePageIndex(fromIndex);
    }

    private void updatePageIndex(int fromIndex) {
        pageIndex = fromIndex / rowsPerPage;
        currPageNumLabel.setText(String.valueOf(pageIndex + 1));
    }
}