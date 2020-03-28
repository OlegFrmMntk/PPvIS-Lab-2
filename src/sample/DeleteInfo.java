package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeleteInfo {
    public void run(ObservableList<Patient> list, Table table){
        SearchAndDeleteModel seatchModel = new SearchAndDeleteModel();
        SearchAndDeleteView form = new SearchAndDeleteView();
        form.sceneOfSearch("Delete");
        Controller controller = new Controller(list);
        form.choose1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!seatchModel.check(form.getInputName1(), form.getInputGroup1())) {
                    form.alert("Enter information");
                    return;
                }

                ObservableList<Patient> dopList = controller.delete(form.getInputName1(), form.getInputGroup1());
                if(dopList.isEmpty()) form.alert("Nothing to delete");
                else form.alertAdding(dopList.size());
                table.updateAll(list);
            }
        });

        form.choose2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!seatchModel.check(form.getInputCourse(),form.getInputLang())){
                    form.alert("Enter information");
                    return;
                }
                ObservableList<Patient> dopList = controller.delete(form.getInputCourse(),form.getInputLang());
                if(dopList.isEmpty()) form.alert("Nothing to delete");
                else form.alertAdding(dopList.size());
                table.updateAll(list);
            }
        });
        form.choose3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Patient> dopList = FXCollections.observableArrayList();
                if(!seatchModel.check(form.getInputCourseOrGroup(),form.getInputWorks())){
                    form.alert("Enter information");
                    return;
                }

                if(!seatchModel.checkSelection(form.getChooseCourse(),form.getChooseGroup())){
                    form.alert("Choose something");
                    return;
                }

             //   dopList = controller.delete(form.getInputCourseOrGroup(), form.getInputWorks(), form.getChooseCourse(),
               //         form.getChooseGroup());
                if(dopList.isEmpty()) form.alert("Nothing to delete");
                else form.alertAdding(dopList.size());
                table.updateAll(list);
            }
        });
        form.choose4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!seatchModel.check(form.getInputCourse4(),form.getInputAllWorks())){
                    form.alert("Enter information");
                    return;
                }
                ObservableList<Patient> dopList = controller.delete(form.getInputCourse4(),form.getInputAllWorks());
                if(dopList.isEmpty()) form.alert("Nothing to delete");
                else form.alertAdding(dopList.size());
                table.updateAll(list);
            }
        });
    }

}
