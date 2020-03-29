package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class SearchAndDeleteModel {

    public boolean check(String checkingInfo1, String checkingInfo2){
        return !checkingInfo1.isEmpty() && !checkingInfo2.isEmpty();
    }

    public boolean checkSelection(boolean one, boolean two){
        return one || two;
    }

    public ObservableList<Patient> checkPattern(String surname, String address, ObservableList<Patient> list) {
        ObservableList<Patient> newTableList = FXCollections.observableArrayList();
        for (Patient patient : list) {
            if (patient.getSurname().equals(surname) && patient.getAddress().equals(address)) {
                newTableList.add(patient);
            }
        }
        return newTableList;
    }

    public ObservableList<Patient> checkPattern(LocalDate date, ObservableList<Patient> list) {
        ObservableList<Patient> newTableList = FXCollections.observableArrayList();
        for (Patient patient : list) {
            if (patient.getBirthDate() == date || patient.getReceiptDate() == date) {
                newTableList.add(patient);
            }
        }
        return newTableList;
    }
/*
    public ObservableList<Patient> checkPattern(String doctorName, ObservableList<Patient> list) {
        ObservableList<Patient> newTableList = FXCollections.observableArrayList();
        for (Patient patient : list) {
            if (patient.getDoctorFio())
        }
    }

 */
}
