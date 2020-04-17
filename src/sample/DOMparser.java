package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMparser {

    public DOMparser(List<Patient> tableData, File file) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();
        Element docRootElement = document.createElement("tableData");

        for (int index = 0; index < tableData.size(); index++) {
            docRootElement.appendChild(addPlayerToDocument(index, tableData.get(index), document));
        }

        document.appendChild(docRootElement);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        saveDataInFile(source, result);
    }

    private Element addPlayerToDocument(int index, Patient patient, Document document) {

        Element patientItem = document.createElement("patient");
        patientItem.setAttribute("id", Integer.toString(index));
        document.appendChild(patientItem);

        Element fullName = document.createElement("fullName");
        fullName.appendChild(document.createTextNode(patient.getFullName()));
        patientItem.appendChild(fullName);

        Element address = document.createElement("address");
        address.appendChild(document.createTextNode(patient.getAddress()));
        patientItem.appendChild(address);

        Element birthDate = document.createElement("birthDate");
        birthDate.appendChild(document.createTextNode(patient.getBirthDate().toString()));
        patientItem.appendChild(birthDate);

        Element receiptDate = document.createElement("receiptDate");
        receiptDate.appendChild(document.createTextNode(patient.getReceiptDate().toString()));
        patientItem.appendChild(receiptDate);

        Element doctorFullName = document.createElement("doctorFullName");
        doctorFullName.appendChild(document.createTextNode(patient.getDoctorFullName()));
        patientItem.appendChild(doctorFullName);

        Element conclusion = document.createElement("conclusion");
        conclusion.appendChild(document.createTextNode(patient.getConclusion()));
        patientItem.appendChild(conclusion);

        return patientItem;
    }

    private void saveDataInFile(DOMSource source, StreamResult result) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }
}
