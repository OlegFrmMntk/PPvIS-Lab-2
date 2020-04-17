package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SAXparser extends DefaultHandler {

    private Controller controller;
    private List<Patient> fileData = new ArrayList<>();
    private String thisElement = "";
    private String fullName = "";
    private String address = "";
    private LocalDate birthDate;
    private LocalDate receiptDate;
    private String doctorFullName = "";
    private String conclusion = "";
    private int readCounter = 0;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
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
                birthDate = controller.parseDate(birthDateString);
                break;
            case "receiptDate":
                String receiptDateString = new String(ch, start, length);
                receiptDate = controller.parseDate(receiptDateString);
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
            fileData.add(new Patient(fullName, address, birthDate, receiptDate, doctorFullName, conclusion));
            readCounter = 0;
        }
        thisElement = "";
    }

    public List<Patient> getFileData() {
        return fileData;
    }
}
