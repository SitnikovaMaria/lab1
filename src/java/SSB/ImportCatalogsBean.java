/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdbc.jdbcCatalog;
import model.Catalog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
@Stateless
public class ImportCatalogsBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
        DocumentBuilderFactory domFactory;
    DocumentBuilder builder;
    Document document;
    
    //"C:\\Users\\user\\Documents\\NetBeansProjects\\ncLab4\\books.xml"
    public ImportCatalogsBean() throws ParserConfigurationException, SAXException, IOException{
        this.domFactory = DocumentBuilderFactory.newInstance();
        this.builder = domFactory.newDocumentBuilder();
        this.document = builder.parse("C:\\Users\\admin\\Documents\\NetBeansProjects\\nclab3\\catalogs.xml");
    }
    
    private String getValue(NodeList fields, int index)
    {
        NodeList list = fields.item(index).getChildNodes();
        if (list.getLength() > 0) {
            return list.item(0).getNodeValue();
        } else {
            return "";
        }
    }
    
    public void Import() throws SQLException, ClassNotFoundException, ParseException{     
        NodeList nodeList = document.getElementsByTagName("catalog");
        NodeList fields = null;
        Catalog catalog= new Catalog();
            for (int s = 0; s < nodeList.getLength(); s++) {                
                jdbcCatalog tmpCatalog = new jdbcCatalog(); 
                Node node = nodeList.item(s);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) node;
                    fields = fstElmnt.getElementsByTagName("name");                    
                    catalog.setName(getValue(fields, 0)); 
                    fields = fstElmnt.getElementsByTagName("nameOfParent");                    
                    catalog.setNameOfParent(getValue(fields, 0)); 
                    tmpCatalog.add(catalog);
                }
            }
        
    }
}
