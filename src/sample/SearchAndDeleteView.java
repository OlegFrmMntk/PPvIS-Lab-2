package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchAndDeleteView {
    public Button choose1, choose2, choose3, choose4;
    private TextField inputName1, inputGroup1, inputCourse, inputLang,inputCourseOrGroup,inputWorks, inputCourse4, inputAllWorks;
    private RadioButton chooseCourse, chooseGroup;
    Stage deletingStage;
    public VBox addingStage;

    public void searchScene(String title) {

        HBox pattern1 = new HBox();
        Label name1 = new Label("Name + group");
        inputName1 = new TextField();
        inputGroup1 = new TextField();
        choose1 = new Button("Click");
        pattern1.getChildren().addAll(name1, inputName1, inputGroup1, choose1);
        inputGroup1.setMaxWidth(100);
        inputName1.setMaxWidth(100);
        pattern1.setSpacing(30);
        pattern1.setPadding(new Insets(10, 20, 20, 20));
        ////////////////////////////////////////////////////////////////////////
        HBox pattern2 = new HBox();
        Label name2 = new Label("course + lang");
        choose2 = new Button("Click");
        inputCourse = new TextField();
        inputLang = new TextField();
        inputCourse.setMaxWidth(100);
        inputLang.setMaxWidth(100);
        pattern2.getChildren().addAll(name2, inputCourse, inputLang, choose2);
        pattern2.setSpacing(30);
        pattern2.setPadding(new Insets(10, 20, 20, 20));

        ////////////////////////////////////////////////////////////////////////////////
        HBox pattern3 = new HBox();
        Label name3 = new Label("course + works");
        choose3 = new Button("Click");
        inputCourseOrGroup = new TextField();
        inputCourseOrGroup.setMaxWidth(100);
        inputWorks = new TextField();
        inputWorks.setMaxWidth(100);
        VBox dopSpace = new VBox();
        HBox radiobut = new HBox();
        chooseCourse = new RadioButton("course");
        chooseGroup = new RadioButton("group");
        ToggleGroup radioGroup = new ToggleGroup();
        chooseCourse.setToggleGroup(radioGroup);
        chooseGroup.setToggleGroup(radioGroup);
        radiobut.getChildren().addAll(chooseCourse, chooseGroup);
        dopSpace.getChildren().addAll(inputCourseOrGroup, radiobut);
        pattern3.getChildren().addAll(name3, dopSpace, inputWorks, choose3);
        pattern3.setSpacing(30);
        pattern3.setPadding(new Insets(10, 20, 20, 20));
        ////////////////////////////////////////////////////
        HBox pattern4 = new HBox();
        Label name4 = new Label("course + all works");
        choose4 = new Button("Click");
        inputCourse4 = new TextField();
        inputAllWorks = new TextField();
        inputCourse4.setMaxWidth(100);
        inputAllWorks.setMaxWidth(100);
        pattern4.getChildren().addAll(name4, inputCourse4, inputAllWorks, choose4);
        pattern4.setSpacing(30);
        pattern4.setPadding(new Insets(10, 20, 20, 20));
        Button returning = new Button("Return");
        returning.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deletingStage.close();
            }
        });

        addingStage = new VBox();
        addingStage.setPadding(new Insets(5, 10, 10, 10));
        addingStage.getChildren().addAll(pattern1, pattern2, pattern3, pattern4, returning);

        deletingStage = new Stage();
        deletingStage.setTitle(title);
        deletingStage.setScene(new Scene(addingStage, 700, 900));
        deletingStage.show();
    }

    public void alert(String text){
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Warning!");
        warn.setContentText(text);
        warn.showAndWait();
    }

    public void alertAdding(int k){
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Warning!");
        warn.setContentText("You have deleted " + k + " rows");
        warn.showAndWait();
    }

    public String getInputGroup1() {
        return inputGroup1.getText();
    }

    public String getInputCourseOrGroup() {
        return inputCourseOrGroup.getText();
    }

    public String getInputCourse4() {
        return inputCourse4.getText();
    }

    public String getInputCourse() {
        return inputCourse.getText();
    }

    public String getInputAllWorks() {
        return inputAllWorks.getText();
    }

    public boolean getChooseGroup() {
        return chooseGroup.isSelected();
    }

    public boolean getChooseCourse() {
        return chooseCourse.isSelected();
    }

    public String getInputLang() {
        return inputLang.getText();
    }

    public String getInputName1() {
        return inputName1.getText();
    }

    public String getInputWorks() {
        return inputWorks.getText();
    }

}

