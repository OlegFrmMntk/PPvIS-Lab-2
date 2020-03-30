package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadInfo extends DefaultHandler {
    enum Element {
        patient, patients, name, surname, address, birthDate, receiptDate, doctorName, doctorSurname, conclusion;
    }

    private ObservableList<Patient> patients;
    private Patient patient;
    private Element thisElem;

    @Override
    public void startElement(String uri, String localName, String nameField, Attributes attributes) throws SAXException {

        switch (nameField) {
            case "patient":
                patient = new Patient();
                thisElem = Element.patient;
                break;
            case "name":
                thisElem = Element.name;
                break;
            case "surname":
                thisElem = Element.surname;
                break;
            case "address":
                thisElem = Element.address;
                break;
            case "birthDate":
                thisElem = Element.birthDate;
                break;
            case "receiptDate":
                thisElem = Element.receiptDate;
                break;
            case "doctorName":
                thisElem = Element.doctorName;
                break;
            case "doctorSurname":
                thisElem = Element.doctorSurname;
                break;
            case "patients":
                patients = FXCollections.observableArrayList();
                thisElem = Element.patients;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();

        if (str.isEmpty()) {
            return;
        }

        if (thisElem.equals(Element.name)) {
            patient.setName(str);
        }
        else if (thisElem.equals(Element.surname)) {
            patient.setSurname(str);
        }
        else if (thisElem.equals(Element.address)) {
            patient.setAddress(str);
        }
        else if (thisElem.equals(Element.birthDate)) {
            // Написать конвертер из строки в дату
            //patient.setBirthDate(str);
        }
        else if (thisElem.equals(Element.receiptDate)) {
            // Написать конвертер из строки в дату
            //patient.setReceiptDate(str);
        }
        else if (thisElem.equals(Element.doctorName)) {
            patient.setDoctorName(str);
        }
        else if (thisElem.equals(Element.doctorSurname)) {
            patient.setDoctorSurname(str);
        }
        else if (thisElem.equals(Element.conclusion)) {
            patient.setConclusion(str);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String nameField) throws SAXException {
        if (nameField.equals("patient")) {
            patients.add(patient);
            patient = null;
        }
    }

    public ObservableList<Patient> getPatientList() {
        return patients;
    }
}


