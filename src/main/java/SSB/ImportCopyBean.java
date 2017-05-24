package SSB;

import jdbc.jdbcCopyOfBook;
import model.CopyOfTheBook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Stateless
public class ImportCopyBean {

    DocumentBuilderFactory domFactory;
    DocumentBuilder builder;
    Document document;

    public ImportCopyBean() throws ParserConfigurationException, SAXException, IOException {
        this.domFactory = DocumentBuilderFactory.newInstance();
        this.builder = domFactory.newDocumentBuilder();
        this.document = builder.parse("D:\\copy.xml");
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
        NodeList nodeList = document.getElementsByTagName("copy");
        NodeList fields = null;
        CopyOfTheBook copy = new CopyOfTheBook();
        boolean er = false;
        jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
        ArrayList<CopyOfTheBook> arrayCopy = new jdbcCopyOfBook().get();
        for (int s = 0; s < nodeList.getLength(); s++) {
            Node node = nodeList.item(s);
            er = false;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element fstElmnt = (Element) node;
                fields = fstElmnt.getElementsByTagName("idBook");
                copy.setIdBook(Long.valueOf(getValue(fields, 0)));
                fields = fstElmnt.getElementsByTagName("issue");
                copy.setIssue(Boolean.valueOf(getValue(fields, 0)));
                fields = fstElmnt.getElementsByTagName("reader");
                copy.setReader(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("idUser");
                copy.setIdUser(Long.valueOf(getValue(fields, 0)));
                tmpCopy.add(copy);
            }
            for (CopyOfTheBook co: arrayCopy){
                if((co.getIdBook()==copy.getIdBook())&&(co.getIssue()==copy.getIssue())&&(co.getIdUser()==copy.getIdUser())){
                    er = true;
                }
            }
            if(!er){
                tmpCopy.add(copy);
            }
        }
    }
}
