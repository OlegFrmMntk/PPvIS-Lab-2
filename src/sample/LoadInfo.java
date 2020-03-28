package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadInfo extends DefaultHandler {
    enum Element {
        patient, patients, name, surname, address, bornDate, receiptDate, doctorFio, conclusion;
    }

    private ObservableList<Patient> patientsList;
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
            case "bornDate":
                thisElem = Element.bornDate;
                break;
            case "receiptDate":
                thisElem = Element.receiptDate;
                break;
            case "doctorFio":
                thisElem = Element.doctorFio;
                break;
            case "patients":
                patientsList = FXCollections.observableArrayList();
                thisElem = Element.patients;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();

        if (str.equals("")) {
            return;
        }

        // Дописать дату
        if (thisElem.equals(Element.name)) {
            patient.setName(str);
        }
        else if (thisElem.equals(Element.surname)) {
            patient.setSurname(str);
        }
        else if (thisElem.equals(Element.address)) {
            patient.setAddress(str);
        }
        else if (thisElem.equals(Element.doctorFio)) {
            patient.setDoctorFio(str);
        }
        else if (thisElem.equals(Element.conclusion)) {
            patient.setConclusion(str);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String nameField) throws SAXException {
        if (nameField.equals("patient")) {
            patientsList.add(patient);
            patient = null;
        }
    }

    public ObservableList<Patient> getPatientsList() {
        return patientsList;
    }
}


