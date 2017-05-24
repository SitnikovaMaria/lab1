package SSB;

import jdbc.jdbcUser;
import model.User;
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
public class ExportU {
    public Element AddUser(Document document, User user) {
        Element userTag = document.createElement("user");
        Element idUserTag = document.createElement("idUser");
        idUserTag.setTextContent(String.valueOf(user.getIdUser()));
        Element nameTag = document.createElement("name");
        nameTag.setTextContent(user.getName());
        Element middleNameTag = document.createElement("middleName");
        middleNameTag.setTextContent(user.getMiddleName());
        Element lastNameTag = document.createElement("lastName");
        lastNameTag.setTextContent(user.getLastName());
        Element typeTag = document.createElement("type");
        typeTag.setTextContent(user.getType());
        Element loginTag = document.createElement("login");
        loginTag.setTextContent(user.getLogin());
        Element passTag = document.createElement("pass");
        passTag.setTextContent(user.getPass());
        userTag.appendChild(idUserTag);
        userTag.appendChild(nameTag);
        userTag.appendChild(middleNameTag);
        userTag.appendChild(lastNameTag);
        userTag.appendChild(typeTag);
        userTag.appendChild(loginTag);
        userTag.appendChild(passTag);
        return userTag;
    }

    public void ExportAll() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            jdbcUser tmpUser = new jdbcUser();
            List<User> userList = tmpUser.get();
            Element root = document.createElement("user");
            document.appendChild(root);
            for(User user: userList){
                root.appendChild(AddUser(document, user));
            }
            createXML(String.valueOf("D:\\user.xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ExportLine(int idUser){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            User userSave = null;
            jdbcUser tmpUser = new jdbcUser();
            List<User> userList = tmpUser.get();
            for(User user: userList){
                if(user.getIdUser()==idUser){
                    userSave = user;
                }
            }
            document.appendChild(AddUser(document, userSave));
            createXML(String.valueOf("D:\\"+"user"+idUser+".xml"), document);
        } catch (ParserConfigurationException | SQLException | ClassNotFoundException | TransformerException ex) {
            Logger.getLogger(ExportU.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createXML(String fileName, Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }
}
