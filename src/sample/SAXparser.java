package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SAXparser extends DefaultHandler {

    private List<Patient> patients = new ArrayList<>();
    private String thisElement = "";
    private String fullName = "";
    private String address = "";
    private LocalDate birthDate;
    private LocalDate receiptDate;
    private String doctorFullName = "";
    private String conclusion = "";
    private int readCounter = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (thisElement) {
            case "fullName":
                fullName = new String(ch, start, length);
                break;
            case "address":
                address = new String(ch, start, length);
                break;
            case "birthDate":
                String birthDateString = new String(ch, start, length);
                birthDate = LocalDate.parse(birthDateString);
                break;
            case "receiptDate":
                String receiptDateString = new String(ch, start, length);
                receiptDate = LocalDate.parse(receiptDateString);
                break;
            case "doctorFullName":
                doctorFullName = new String(ch, start, length);
                break;
            case "conclusion":
                conclusion = new String(ch, start, length);
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (!thisElement.equals("tableDate")) {
            readCounter++;
        }
        if (readCounter == 7) {
            patients.add(new Patient(fullName, address, birthDate, receiptDate, doctorFullName, conclusion));
            readCounter = 0;
        }
        thisElement = "";
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
