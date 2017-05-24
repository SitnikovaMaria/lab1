package SSB;

import jdbc.jdbcCopyOfBook;
import model.CopyOfTheBook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ExportC {
    public Element AddCopy(Document document, CopyOfTheBook copy) {
        Element copyTag = document.createElement("copy");
        Element inventoryNumberTag = document.createElement("inventoryNumber");
        inventoryNumberTag.setTextContent(String.valueOf(copy.getInventoryNumber()));
        Element idBookTag = document.createElement("idBook");
        idBookTag.setTextContent(String.valueOf(copy.getIdBook()));
        Element issueTag = document.createElement("issue");
        issueTag.setTextContent(String.valueOf(copy.getIssue()));
        Element readerTag = document.createElement("reader");
        readerTag.setTextContent(copy.getReader());
        Element idUserTag = document.createElement("idUser");
        idUserTag.setTextContent(String.valueOf(copy.getIdUser()));
        copyTag.appendChild(inventoryNumberTag);
        copyTag.appendChild(idBookTag);
        copyTag.appendChild(issueTag);
        copyTag.appendChild(readerTag);
        copyTag.appendChild(idUserTag);
        return copyTag;
    }

    public void ExportAll() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
            List<CopyOfTheBook> copyList = tmpCopy.get();
            Element root = document.createElement("copy");
            document.appendChild(root);
            for(CopyOfTheBook copy: copyList){
                root.appendChild(AddCopy(document, copy));
            }
            createXML(String.valueOf("D:\\copy.xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ExportLine(int inventoryNumber){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            CopyOfTheBook copySave = null;
            jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
            List<CopyOfTheBook> copyList = tmpCopy.get();
            for(CopyOfTheBook copy: copyList){
                if(copy.getInventoryNumber()==inventoryNumber){
                    copySave = copy;
                }
            }
            document.appendChild(AddCopy(document, copySave));
            createXML(String.valueOf("D:\\"+"copy"+inventoryNumber+".xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createXML(String fileName, Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }
}
