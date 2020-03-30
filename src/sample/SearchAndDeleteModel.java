package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class SearchAndDeleteModel {

    public boolean check(String name, String surname) {
        return !name.isEmpty() && !surname.isEmpty();
    }

    public boolean checkSelection(boolean one, boolean two) {
        return one || two;
    }

    public ObservableList<Patient>
    checkPattern(String data, boolean IsSurname, ObservableList<Patient> patientList) {
        ObservableList<Patient> newPatientList = FXCollections.observableArrayList();
        for (Patient patient : patientList) {
            if ((IsSurname && patient.getSurname().equals(data)) ||
                    (!IsSurname && patient.getAddress().equals(data))) {
                newPatientList.add(patient);
            }
        }
        return newPatientList;
    }

    public ObservableList<Patient>
    checkPattern(LocalDate date, boolean isBirthDate, ObservableList<Patient> patientList) {
        ObservableList<Patient> newPatientList = FXCollections.observableArrayList();
        for (Patient patient : patientList) {
            if ((isBirthDate && date.isEqual(patient.getBirthDate())) ||
                    (!isBirthDate && date.isEqual(patient.getReceiptDate()))) {
                newPatientList.add(patient);
            }
        }
        return newPatientList;
    }

    public ObservableList<Patient>
    checkPattern(String name, String surname, ObservableList<Patient> patientList) {
        ObservableList<Patient> newPatientList = FXCollections.observableArrayList();
        for (Patient patient : patientList) {
            if (name.isEmpty()) {
                name = patient.getDoctorName();
            }
            if (surname.isEmpty()) {
                surname = patient.getDoctorSurname();
            }

            if (name.equals(patient.getDoctorName()) && surname.equals(patient.getDoctorSurname())) {
                newPatientList.add(patient);
            }
        }
        return newPatientList;
    }
}
