package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchWindow {

    public SearchWindow(Stage primaryStage, Controller controller) {

        SearchWindowTableGroup tableGroup = new SearchWindowTableGroup(controller);
        SearchDeleteGroup searchGroup = new SearchDeleteGroup(controller, tableGroup, "Search");

        BorderPane layout = new BorderPane();

        layout.setRight(tableGroup.getGroup());
        layout.setLeft(searchGroup.getGroup());

        Scene scene = new Scene(layout, 1000, 520);
        Stage stage = new Stage();
        stage.setTitle("Search patients");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setX(primaryStage.getX() + 100);
        stage.setY(primaryStage.getY() + 100);

        stage.show();
    }

}
