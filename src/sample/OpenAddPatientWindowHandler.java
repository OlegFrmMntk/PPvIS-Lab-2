package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class OpenAddPatientWindowHandler implements EventHandler<ActionEvent>  {

    private final MainWindowTableGroup tableGroup;
    private final Stage stage;
    private final Controller controller;

    public OpenAddPatientWindowHandler(MainWindowTableGroup tableGroup, Stage stage, Controller controller) {
        this.tableGroup = tableGroup;
        this.stage = stage;
        this.controller = controller;
    }


    @Override
    public void handle(ActionEvent event) {
        new AddPatientWindow(tableGroup, stage, controller);
    }
}
