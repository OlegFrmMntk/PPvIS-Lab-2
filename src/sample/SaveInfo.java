package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sample.Patient;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaveInfo {

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public void write(ObservableList<Patient> patientList){

        List<String> name = new ArrayList<>();
        List<String> surname = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<SimpleObjectProperty<LocalDate>> bornDate = new ArrayList<>();
        List<SimpleObjectProperty<LocalDate>> receiptDate = new ArrayList<>();
        List<String> doctorFio = new ArrayList<>();
        List<String> conclusion = new ArrayList<>();

        for (Patient info : patientList) {
            name.add(info.getName());
            surname.add(info.getSurname());
            address.add(info.getAddress());
            bornDate.add(info.getBornDateProperty());
            receiptDate.add(info.getReceiptDateProperty());
            doctorFio.add(info.getDoctorFio());
            conclusion.add(info.getConclusion());
        }

        try {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element company = document.createElement("Patient");
                document.appendChild(company);

                for (int i = 0; i < patientList.size(); i++) {
                    Element nameTag = document.createElement("name");
                    Element surnameTag = document.createElement("surname");
                    Element addressTag = document.createElement("address");
                    Element bornDateTag = document.createElement("bornDate");
                    Element receiptDateTag = document.createElement("receiptDate");
                    Element doctorFioTag = document.createElement("doctorFio");
                    Element conclusionTag = document.createElement("conclusion");
                    Element patient = document.createElement("patient");

                    nameTag.setTextContent(name.get(i));
                    surnameTag.setTextContent(surname.get(i));
                    addressTag.setTextContent(address.get(i));
                    //bornDateTag.setTextContent(bornDate.get(i));
                    //receiptDateTag.setTextContent(receiptDate.get(i));
                    doctorFioTag.setTextContent(doctorFio.get(i));
                    conclusionTag.setTextContent(conclusion.get(i));

                    company.appendChild(patient);
                    patient.appendChild(nameTag);
                    patient.appendChild(surnameTag);
                    patient.appendChild(addressTag);
                    patient.appendChild(bornDateTag);
                    patient.appendChild(receiptDateTag);
                    patient.appendChild(doctorFioTag);
                    patient.appendChild(conclusionTag);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Документ сохранен!");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }



}