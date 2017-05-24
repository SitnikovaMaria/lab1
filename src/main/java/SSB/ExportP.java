package SSB;

import jdbc.jdbcPublisher;
import model.Publisher;
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
public class ExportP {
    public Element AddPublisher(Document document, Publisher publisher) {
        Element publisherTag = document.createElement("publisher");
        Element idPublisherTag = document.createElement("idPublisher");
        idPublisherTag.setTextContent(String.valueOf(publisher.getIdPublisher()));
        Element nameTag = document.createElement("name");
        nameTag.setTextContent(publisher.getName());
        Element registeredAddressTag = document.createElement("registeredAddress");
        registeredAddressTag.setTextContent(publisher.getRegisteredAddress());
        Element businessAddressTag = document.createElement("businessAddress");
        businessAddressTag.setTextContent(String.valueOf(publisher.getBusinessAddress()));
        publisherTag.appendChild(idPublisherTag);
        publisherTag.appendChild(nameTag);
        publisherTag.appendChild(registeredAddressTag);
        publisherTag.appendChild(businessAddressTag);
        return publisherTag;
    }

    public void ExportAll() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            jdbcPublisher tmpPublisher = new jdbcPublisher();
            List<Publisher> publisherList = tmpPublisher.get();
            Element root = document.createElement("publisher");
            document.appendChild(root);
            for(Publisher publisher: publisherList){
                root.appendChild(AddPublisher(document, publisher));
            }
            createXML(String.valueOf("D:\\publisher.xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ExportLine(int idPublisher){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Publisher publisherSave = null;
            jdbcPublisher tmpPublisher = new jdbcPublisher();
            List<Publisher> publisherList = tmpPublisher.get();
            for(Publisher publisher: publisherList){
                if(publisher.getIdPublisher()==idPublisher){
                    publisherSave = publisher;
                }
            }
            document.appendChild(AddPublisher(document, publisherSave));
            createXML(String.valueOf("D:\\"+"publisher"+idPublisher+".xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createXML(String fileName, Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }
}
