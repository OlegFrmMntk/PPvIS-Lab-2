package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Patient {

    private StringProperty fullName;
    private StringProperty address;
    private SimpleObjectProperty<LocalDate> birthDate;
    private SimpleObjectProperty<LocalDate> receiptDate;
    private StringProperty fullDoctorName;
    private StringProperty conclusion;

    public Patient(String fullName, String address, LocalDate birthDate, LocalDate receiptDate,
                   String fullDoctorName, String conclusion) {

        this.fullName = new SimpleStringProperty(fullName);
        this.address = new SimpleStringProperty(address);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.receiptDate = new SimpleObjectProperty<>(receiptDate);
        this.fullDoctorName = new SimpleStringProperty(fullDoctorName);
        this.conclusion = new SimpleStringProperty(conclusion);
    }

    public String getFullName() {
        return fullName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public LocalDate getReceiptDate() { return receiptDate.get(); }

    public String getDoctorFullName() {
        return fullDoctorName.get();
    }

    public String getConclusion() {
        return conclusion.get();
    }
}
