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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdbc.jdbcBook;
import model.Book;

/**
 *
 * @author user
 */


@Stateless
public class Export {
    public Element AddBook(Document document, Book book) {
        Element bookTag = document.createElement("book");
            Element idTag = document.createElement("id");
            idTag.setTextContent(String.valueOf(book.getIdBook()));
            Element nameTag = document.createElement("name");
            nameTag.setTextContent(book.getName());
            Element authorTag = document.createElement("author");
            authorTag.setTextContent(book.getAuthors());
            Element yearTag = document.createElement("year");
            yearTag.setTextContent(String.valueOf(book.getYear()));
            Element pagesTag = document.createElement("page");
            pagesTag.setTextContent(String.valueOf(book.getPages()));
            Element catalogTag = document.createElement("catalog");
            catalogTag.setTextContent(book.getCatalog());
            Element publisherTag = document.createElement("publisher");
            publisherTag.setTextContent(book.getPublisher());
            bookTag.appendChild(idTag);
            bookTag.appendChild(nameTag);
            bookTag.appendChild(authorTag);
            bookTag.appendChild(yearTag);
            bookTag.appendChild(pagesTag);
            bookTag.appendChild(catalogTag);
            bookTag.appendChild(publisherTag);
        return bookTag;
    }

    public void ExportAll() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            jdbcBook tmpBook = new jdbcBook();
            List<Book> bookList = tmpBook.get();
            Element root = document.createElement("books");
            document.appendChild(root);
            for(Book book: bookList){
                root.appendChild(AddBook(document, book));
            }
            
            
            
            createXML(String.valueOf("CC:\\Users\\admin\\Documents\\NetBeansProjects\\nclab3\\books.xml"), document);
            
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ExportLine(int idBook){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Book bookSave = null;
            jdbcBook tmpBook = new jdbcBook();
            List<Book> bookList = tmpBook.get();
            for(Book book: bookList){
                if(book.getIdBook()==idBook){
                    bookSave = book;
                }
            }
            
            document.appendChild(AddBook(document, bookSave));
            
            createXML(String.valueOf("C:\\Users\\admin\\Documents\\NetBeansProjects\\nclab3\\"+"book"+idBook+".xml"), document);
            
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void createXML(String fileName, Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
