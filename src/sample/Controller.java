package sample;

import javafx.collections.ObservableList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class
Controller {
    LoadInfo saxReader;
    SaveInfo writerXML;
    ObservableList<Patient> patients;

    public Controller(ObservableList<Patient> patients) {
        this.patients = patients;

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

            list.addAll(saxReader.getPatientsList());
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

    public ObservableList<Patient> search(String surname, String address) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        return pattern.checkPattern(surname, address, patients);
    }
/*
    public ObservableList<Patient> search(String text1, String text2, boolean chooseCourse, boolean chooseGroup) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Patient> dopList = pattern.checkPattern(text1, text2, patients, chooseCourse, chooseGroup);
        return dopList;
    }
*/
    public ObservableList<Patient> delete(String surname, String address) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Patient> newPatientsList = pattern.checkPattern(surname, address, patients);
        patients.removeAll(newPatientsList);
        return newPatientsList;
    }
/*
    public ObservableList<Patient> delete(String text1, String text2, boolean chooseCourse, boolean chooseGroup) {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Patient> dopList = pattern.checkPattern(text1, text2, patients, chooseCourse, chooseGroup);
        patients.removeAll(dopList);
        return dopList;
    }
*/
    public void add(Patient someObj) {
        patients.add(someObj);
    }

    public void exit() {
        System.exit(0);
    }
}

