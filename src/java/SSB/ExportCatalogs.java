/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdbc.jdbcCatalog;
import model.Book;
import model.Catalog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
@Stateless
public class ExportCatalogs {

        public Element AddBook(Document document, Catalog catalog) {
        Element catalogTag = document.createElement("catalog");
            Element idTag = document.createElement("id");
            idTag.setTextContent(String.valueOf(catalog.getIdCatalog()));
            Element nameTag = document.createElement("name");
            nameTag.setTextContent(String.valueOf(catalog.getName()));
            Element nameOfParentTag = document.createElement("nameOfParent");
            nameOfParentTag.setTextContent(String.valueOf(catalog.getNameOfParent()));
            catalogTag.appendChild(idTag);
            catalogTag.appendChild(nameTag);
            catalogTag.appendChild(nameOfParentTag);
            
        return catalogTag;
    }
        
    public void ExportAll() throws SQLException {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            jdbcCatalog tmpCatalog = new jdbcCatalog();
            List<Catalog> catalogList = tmpCatalog.get();
            Element root = document.createElement("catalogs");
            document.appendChild(root);
            for(Catalog catalog: catalogList){
                root.appendChild(AddBook(document, catalog));        
            }      
            createXML(String.valueOf("C:\\Users\\admin\\Documents\\NetBeansProjects\\nclab3\\catalogs.xml"), document);
            
        } catch (ParserConfigurationException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
   private void createXML(String fileName, Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
    }
   
   
}
