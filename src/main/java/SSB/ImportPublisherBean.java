package SSB;

import jdbc.jdbcPublisher;
import model.Publisher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import servlets.ImportPublisher;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ImportPublisherBean {

    DocumentBuilderFactory domFactory;
    DocumentBuilder builder;
    Document document;

    public ImportPublisherBean() throws ParserConfigurationException, SAXException, IOException {
        this.domFactory = DocumentBuilderFactory.newInstance();
        this.builder = domFactory.newDocumentBuilder();
        this.document = builder.parse("D:\\publisher.xml");
    }

    private String getValue(NodeList fields, int index) {
        NodeList list = fields.item(index).getChildNodes();
        if (list.getLength() > 0) {
            return list.item(0).getNodeValue();
        } else {
            return "";
        }
    }

    public void Import() throws SQLException, ClassNotFoundException, ParseException {
        NodeList nodeList = document.getElementsByTagName("publisher");
        NodeList fields = null;
        Publisher publisher = new Publisher();
        boolean er = false;
        ArrayList<Publisher> arrayPublisher = new jdbcPublisher().get();
        jdbcPublisher tmpPublisher = new jdbcPublisher();
        for (int s = 0; s < nodeList.getLength(); s++) {
            Node node = nodeList.item(s);
            er = false;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element fstElmnt = (Element) node;
                fields = fstElmnt.getElementsByTagName("name");
                publisher.setName(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("registeredAddress");
                publisher.setRegisteredAddress(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("businessAddress");
                publisher.setBusinessAddress(getValue(fields, 0));
            }
            for (Publisher pub: arrayPublisher){
                if((pub.getName().equals(publisher.getName()))&&(pub.getRegisteredAddress().equals(publisher.getRegisteredAddress()))&&(pub.getBusinessAddress().equals(publisher.getBusinessAddress()))){
                    er = true;
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "ER "+er);
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "Name1 "+pub.getName());
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "Name2 "+publisher.getName());
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "RegisteredAddress1 "+pub.getRegisteredAddress());
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "RegisteredAddress2 "+publisher.getRegisteredAddress());
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "BusinessAddress1 "+pub.getBusinessAddress());
                    Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "BusinessAddress2 "+publisher.getBusinessAddress());
                }
            }
            if(!er){
                Logger.getLogger(ImportPublisher.class.getName()).log(Level.SEVERE, "ER "+er);
                tmpPublisher.add(publisher);
            }
        }
    }
}
