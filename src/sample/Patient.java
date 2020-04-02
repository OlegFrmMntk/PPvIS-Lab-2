package sample;

import java.time.LocalDate;

public class Patient {
    private String name;
    private String surname;
    private String address;
    private LocalDate birthDate;
    private LocalDate receiptDate;
    private String doctorName;
    private String doctorSurname;
    private String conclusion;

    public Patient() {
        this.name = "";
        this.surname = "";
        this.address = "";
        this.birthDate = null;
        this.receiptDate = null;
        this.doctorName = "";
        this.doctorSurname = "";
        this.conclusion = "";
    }

    public Patient(String name, String surname, String address, LocalDate bornDate,
                   LocalDate receiptDate, String doctorName, String doctorSurname, String conclusion) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = bornDate;
        this.receiptDate = receiptDate;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.conclusion = conclusion;
    }

    public void setName(String name) { this.name = name; }

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

    public LocalDate getBirthDate() { return this.birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public LocalDate getReceiptDate() { return this.receiptDate; }

    public void setReceiptData(LocalDate receiptDate) { this.receiptDate = receiptDate; }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return this.doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorName = doctorSurname;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

}
