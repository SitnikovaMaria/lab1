/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Book;
import jdbc.jdbcBook;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
@Stateless
public class ImportBooksBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    DocumentBuilderFactory domFactory;
    DocumentBuilder builder;
    Document document;
    
    //"C:\\Users\\user\\Documents\\NetBeansProjects\\ncLab4\\books.xml"
    public ImportBooksBean() throws ParserConfigurationException, SAXException, IOException{
        this.domFactory = DocumentBuilderFactory.newInstance();
        this.builder = domFactory.newDocumentBuilder();
        this.document = builder.parse("C:\\Users\\admin\\Documents\\NetBeansProjects\\nclab3\\books.xml");
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
        NodeList nodeList = document.getElementsByTagName("book");
        NodeList fields   = null;
        Book book= new Book();
            for (int s = 0; s < nodeList.getLength(); s++) {                
                jdbcBook tmpBook = new jdbcBook(); 
                Node node = nodeList.item(s);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) node;
                    fields = fstElmnt.getElementsByTagName("name");                    
                    book.setName(getValue(fields, 0)); 
                    fields = fstElmnt.getElementsByTagName("author");                    
                    book.setAuthors(getValue(fields, 0)); 
                    fields = fstElmnt.getElementsByTagName("year");  
                    
                    String year = getValue(fields,0).substring(getValue(fields,0).length()-4);
                    book.setYear(year);
                    fields = fstElmnt.getElementsByTagName("page");                    
                    book.setPages(Integer.valueOf(getValue(fields, 0))); 
                    fields = fstElmnt.getElementsByTagName("catalog");                    
                    book.setCatalog(getValue(fields, 0));
                    fields = fstElmnt.getElementsByTagName("publisher");                    
                    book.setPublisher(getValue(fields, 0)); 
                    tmpBook.add(book);
                }
            }
        
    }
}
