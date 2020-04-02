package sample;

import javafx.collections.ObservableList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Controller {

    LoadInfo saxReader;
    SaveInfo writerXML;
    ObservableList<Patient> patientList;

    public Controller(ObservableList<Patient> patients) {
        this.patientList = patients;

    }

    public boolean open(File file, ObservableList<Patient> list) {
        if (saxReader == null)  {
            saxReader = new LoadInfo();
        }

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, saxReader);
            list.clear();

            list.addAll(saxReader.getPatientList());
            return true;
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(File file, ObservableList<Patient> list) {
        if (writerXML == null) {
            writerXML = new SaveInfo();
        }

        writerXML.setFile(file);

        try {
            writerXML.write(list);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ObservableList<Patient> search(String data, boolean isSurname) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        return pattern.checkPattern(data, isSurname, patientList);
    }

    public ObservableList<Patient> search(LocalDate date, boolean isBirthDate) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        return  pattern.checkPattern(date, isBirthDate, patientList);
    }


    public ObservableList<Patient> delete(String data, boolean isSurname) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Patient> newPatientList = pattern.checkPattern(data, isSurname, patientList);
        patientList.removeAll(newPatientList);
        return newPatientList;
    }

    public  ObservableList<Patient> delete(LocalDate date, boolean isBirthDate) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Patient> newPatientList = pattern.checkPattern(date, isBirthDate, patientList);
        patientList.removeAll(newPatientList);
        return newPatientList;
    }
    public void add(Patient someObj) {
        patientList.add(someObj);
    }

    public void exit() {
        System.exit(0);
    }
}

