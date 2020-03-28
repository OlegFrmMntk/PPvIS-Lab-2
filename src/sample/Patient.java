package sample;

import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class Patient {
    private String name;
    private String surname;
    private String fio;
    private String address;
    private SimpleObjectProperty<LocalDate> bornDate;
    private SimpleObjectProperty<LocalDate> receiptDate;
    private String doctorFio;
    private String conclusion;

    public Patient() {
        this.name = "";
        this.surname = "";
        this.address = "";
        this.bornDate = null;
        this.receiptDate = null;
        this.doctorFio = "";
        this.conclusion = "";
    }

    public Patient(String name, String surname, String address,
                   SimpleObjectProperty<LocalDate> bornDate, SimpleObjectProperty<LocalDate> receiptDate,
                   String doctorName, String doctorSurname, String conclusion) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.bornDate = bornDate;
        this.receiptDate = receiptDate;
        this.doctorFio = doctorName + " " + doctorSurname;
        this.conclusion = conclusion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBornDate() {
        return this.bornDate.get();
    }

    public SimpleObjectProperty<LocalDate> getBornDateProperty() {
        return this.bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate.set(bornDate);
    }

    public LocalDate getReceiptDate() {
         return this.receiptDate.get();
    }

    public SimpleObjectProperty<LocalDate> getReceiptDateProperty() {
        return this.receiptDate;
    }

    public void setReceiptData(LocalDate receiptDate) {
        this.receiptDate.set(receiptDate);
    }

    public String getDoctorFio() {
        return this.doctorFio;
    }

    public void setDoctorFio(String doctorFio) {
        this.doctorFio = doctorFio;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

}
