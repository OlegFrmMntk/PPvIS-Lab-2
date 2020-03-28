package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SearchInfo{
    public void run(ObservableList<Patient> list){
        ObservableList<Patient> newPatientsList = FXCollections.observableArrayList();

        SearchAndDeleteModel searchModel = new SearchAndDeleteModel();
        SearchAndDeleteView form = new SearchAndDeleteView();

        Table table = new Table();
        VBox newTableForm = table.create(newPatientsList);
        TableView newTable = new TableView();
        new Table().createTable(newTable);

        form.sceneOfSearch("Search");
        form.stageForAdding.getChildren().add(newTableForm);

        Controller controller = new Controller(list);

        form.choose1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newPatientsList.clear();

                if (!searchModel.check(form.getInputName1(),form.getInputGroup1())) {
                    form.alert("Enter information");
                    return;
                }

                ObservableList<Patient> newPatientsList = controller.search(form.getInputName1(),form.getInputGroup1());
                table.updateAll(newPatientsList);
            }
        });
        form.choose2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newPatientsList.clear();

                if(!searchModel.check(form.getInputCourse(),form.getInputLang())){
                    form.alert("Enter information");
                    return;
                }
                ObservableList<Patient> dopList = controller.search(form.getInputCourse(),form.getInputLang());
                table.updateAll(dopList);
            }
        });
        form.choose3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newPatientsList.clear();

                ObservableList<Patient> dopList = FXCollections.observableArrayList();
                if(!searchModel.check(form.getInputCourseOrGroup(),form.getInputWorks())){
                    form.alert("Enter information");
                }
                else if (!searchModel.checkSelection(form.getChooseCourse(),form.getChooseGroup())){
                    form.alert("Choose something");
                }
                else {
                    //  dopList = controller.search(form.getInputCourseOrGroup(), form.getInputWorks(), form.getChooseCourse(),form.getChooseGroup());;
                    //table.updateAll(dopList);
                }
            }
        });
        form.choose4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newPatientsList.clear();

                if(!searchModel.check(form.getInputCourse4(),form.getInputAllWorks())){
                    form.alert("Enter information");
                }
                else {
                    ObservableList<Patient> dopList = controller.search(form.getInputCourse4(), form.getInputAllWorks());
                    table.updateAll(dopList);
                }
            }
        });
    }
}
