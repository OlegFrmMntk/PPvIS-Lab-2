package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SearchInfo{
    public void run(ObservableList<Patient> list){
        ObservableList<Patient> dopList = FXCollections.observableArrayList();
        SearchAndDeleteModel seatchModel = new SearchAndDeleteModel();
        SearchAndDeleteView form = new SearchAndDeleteView();
        Table table = new Table();
        VBox tableNew = table.create(dopList);
        TableView newTable = new TableView();
        new Table().createTable(newTable);
        form.sceneOfSearch("Search");
        form.stageForAdding.getChildren().add(tableNew);
        Controller controller = new Controller(list);
        form.choose1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                while(dopList.size()!=0) {
                    dopList.remove(0);
                }
                if(!seatchModel.check(form.getInputName1(),form.getInputGroup1())){
                    form.alert("Enter information");
                    return;
                }
                ObservableList<Patient> dopList = controller.search(form.getInputName1(),form.getInputGroup1());
                table.updateAll(dopList);
            }
        });
        form.choose2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                while(dopList.size()!=0) {
                    dopList.remove(0);
                }
                if(!seatchModel.check(form.getInputCourse(),form.getInputLang())){
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
                while(dopList.size()!=0) {
                    dopList.remove(0);
                }
                ObservableList<Patient> dopList = FXCollections.observableArrayList();
                if(!seatchModel.check(form.getInputCourseOrGroup(),form.getInputWorks())){
                    form.alert("Enter information");
                    return;
                }

                if(!seatchModel.checkSelection(form.getChooseCourse(),form.getChooseGroup())){
                    form.alert("Choose something");
                    return;
                }
              //  dopList = controller.search(form.getInputCourseOrGroup(), form.getInputWorks(), form.getChooseCourse(),form.getChooseGroup());;
                //table.updateAll(dopList);
            }
        });
        form.choose4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                while(dopList.size()!=0) {
                    dopList.remove(0);
                }
                if(!seatchModel.check(form.getInputCourse4(),form.getInputAllWorks())){
                    form.alert("Enter information");
                    return;
                }
                ObservableList<Patient> dopList = controller.search(form.getInputCourse4(),form.getInputAllWorks());
                table.updateAll(dopList);
            }
        });
    }
}
