package sample;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller {

    private List<Patient> mainTableData = new ArrayList<>();
    private List<Patient> searchTableData = new ArrayList<>();
    private DOMparser parser;

    public void addPatientToArray(String fullName, String address, LocalDate birthDate,
                                 LocalDate receiptDate, String fullDoctorName, String conclusion) {

        Patient patient = new Patient(fullName, address, birthDate, receiptDate, fullDoctorName, conclusion);
        mainTableData.add(patient);
    }

    public Page<Patient> updateMainWindowTableView(int pageNumber, int recordsOnPageCount) {
        return createPage(pageNumber, recordsOnPageCount, mainTableData);
    }

    public Page<Patient> createPage(int pageNumber, int recordsOnPageCount, List<Patient> tableData) {
        int pageCount = 1;
        if (tableData.size() > recordsOnPageCount) {
            if (tableData.size() % recordsOnPageCount != 0) {
                pageCount = (tableData.size() - tableData.size() % recordsOnPageCount) / recordsOnPageCount + 1;
            }
            else {
                pageCount = (tableData.size() - tableData.size() % recordsOnPageCount) / recordsOnPageCount;
            }
        }

        List<Patient> pageData;
        if (pageCount != pageNumber && pageCount != 0) {
            pageData = tableData.subList((pageNumber - 1) * recordsOnPageCount,
                                        (pageNumber - 1) * recordsOnPageCount + recordsOnPageCount);
        }
        else {
            pageData = tableData.subList((pageNumber - 1) * recordsOnPageCount,
                                            tableData.size());
        }

        Page<Patient> page = new Page<>(pageNumber, pageData, pageCount, tableData.size());
        pageNumber = page.getPageNumber();
        return page;
    }

    public void saveTableData(File file, DOMparser parser) throws TransformerException, ParserConfigurationException {
        parser.parse(mainTableData, file);
    }

    public void getTableDataFromFile(File file, SAXParser parser) throws ParserConfigurationException, SAXException,
                                                                                                       IOException {
        SAXparser saxParser = new SAXparser();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        parser = factory.newSAXParser();
        parser.parse(file, saxParser);

        mainTableData = saxParser.getPatients();
    }

    public void fullNameAndAddress(String fullName, String address) {
        List<Patient> searchResult = new ArrayList<>();

        for (Patient patient : mainTableData) {
            if (patient.getFullName().contains(fullName) && patient.getAddress().contains(address)) {
                searchResult.add(patient);
            }
        }
        searchTableData = searchResult;
    }

    public void birthDate(LocalDate birthDate) {
        List<Patient> searchResult = new ArrayList<>();

        for (Patient mainTableDatum : mainTableData) {
            if (mainTableDatum.getBirthDate().toString().equals(birthDate.toString())) {
                searchResult.add(mainTableDatum);
            }
        }
        searchTableData = searchResult;
    }

    public void receiptDateOrDoctorFullName(LocalDate receiptDate, String doctorFullName) {
        List<Patient> searchResult = new ArrayList<>();

        for (Patient patient : mainTableData) {
            if (doctorFullName.isEmpty()) {
                if (patient.getReceiptDate().toString().equals(receiptDate.toString())) {
                    searchResult.add(patient);
                }
            }
            else if (patient.getDoctorFullName().contains(doctorFullName)) {
                    searchResult.add(patient);
            }
        }
        searchTableData = searchResult;
    }

    public Page<Patient> updateSearchWindowTable(int pageNumber, int recordsOnPageCount) {
        return createPage(pageNumber, recordsOnPageCount, searchTableData);
    }

    public int fullNameAndAddressDelete(String fullName, String address) {

        int deleteNumber = 0;
        for (Iterator<Patient> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Patient patient = iterator.next();
            if (patient.getFullName().contains(fullName) && patient.getAddress().contains(address)) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public int birthDateDelete(LocalDate birthDate) {

        int deleteNumber = 0;
        for (Iterator<Patient> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Patient patient = iterator.next();
            if (patient.getBirthDate().toString().equals(birthDate.toString())) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public int receiptDateOrDoctorFullNameDelete(LocalDate receiptDate, String doctorFullName) {

        int deleteNumber = 0;
        for (Iterator<Patient> iterator = mainTableData.iterator(); iterator.hasNext();) {
            Patient patient = iterator.next();
            if (doctorFullName.isEmpty()) {
                if (patient.getReceiptDate().toString().equals(receiptDate.toString())) {
                    iterator.remove();
                    deleteNumber++;
                }
            }
            else if (patient.getDoctorFullName().contains(doctorFullName)) {
                iterator.remove();
                deleteNumber++;
            }
        }
        return deleteNumber;
    }

    public void exit() {
        System.exit(0);
    }
}
