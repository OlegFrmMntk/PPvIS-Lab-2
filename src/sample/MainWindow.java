package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class MainWindow extends Application {

    private Controller controller = new Controller();
    private MainWindowTableGroup tableGroup = new MainWindowTableGroup(controller);
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        MenuBar menuBar = getMenuBar();
        root.setTop(menuBar);

        //Group toolBar = getToolBar(primaryStage);
        //root.setLeft(toolBar);

        Group tableBar = tableGroup.getGroup();
        root.setRight(tableBar);

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Patient data base");
        primaryStage.setScene(new Scene(root, 750, 520));
        primaryStage.show();
    }

    private MenuBar getMenuBar() {

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(actionEvent -> {
            try {
                saveTableData();
            }
            catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        });

        MenuItem getItem = new MenuItem("Load");
        getItem.setOnAction(actionEvent -> {
            try {
                getTableDataFromFile();
            }
            catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        });

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(actionEvent -> {
            controller.exit();
        });

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(saveItem, getItem, exitItem);

        MenuItem addItem = new MenuItem("Add");
        addItem.setOnAction(actionEvent -> new AddPatientWindow(tableGroup, primaryStage, controller));

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(actionEvent -> new DeleteWindow(primaryStage, controller, tableGroup));

        MenuItem searchItem = new MenuItem("Search");
        searchItem.setOnAction(actionEvent -> new SearchWindow(primaryStage, controller));

        Menu patientMenu = new Menu("Patient");
        patientMenu.getItems().addAll(addItem, searchItem, deleteItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, patientMenu);
        return menuBar;
    }

    private Group getToolBar(Stage primaryStage) {

        Button openAddStudentWindowButton = new Button("Add Patient");
        openAddStudentWindowButton.setPrefSize(100, 40);

        openAddStudentWindowButton.setOnAction(new OpenAddPatientWindowHandler(tableGroup, primaryStage, controller));

        Button openDeleteWindowButton = new Button("Delete");
        openDeleteWindowButton.setOnAction(actionEvent -> new DeleteWindow(primaryStage, controller, tableGroup));
        openDeleteWindowButton.setPrefSize(100, 40);
        openDeleteWindowButton.setLayoutY(50);

        Button openSearchWindowButton = new Button("Search");
        openSearchWindowButton.setOnAction(actionEvent -> new SearchWindow(primaryStage, controller));
        openSearchWindowButton.setPrefSize(100, 40);
        openSearchWindowButton.setLayoutY(100);

        Button saveButton = new Button("Save data");
        saveButton.setOnAction(actionEvent -> {
            try {
                saveTableData();
            } catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        });
        saveButton.setPrefSize(100, 40);
        saveButton.setLayoutY(150);

        Button downloadButton = new Button("Get data");
        downloadButton.setOnAction(actionEvent -> {
            try {
                getTableDataFromFile();
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        });
        downloadButton.setPrefSize(100, 40);
        downloadButton.setLayoutY(200);

        Group toolBar = new Group();
        toolBar.getChildren().addAll(openAddStudentWindowButton, openDeleteWindowButton,
                openSearchWindowButton, saveButton, downloadButton);

        return toolBar;

    }

    public void saveTableData() throws TransformerException, ParserConfigurationException {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            controller.saveTableData(file);
        }
    }

    public void getTableDataFromFile() throws IOException, SAXException, ParserConfigurationException {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            controller.getTableDataFromFile(file);
            tableGroup.updateTable();
            tableGroup.setPageNumber(1);
        }
    }

}
