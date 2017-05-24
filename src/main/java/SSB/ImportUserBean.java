package SSB;

import jdbc.jdbcPublisher;
import jdbc.jdbcUser;
import model.User;
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
public class ImportUserBean {

    DocumentBuilderFactory domFactory;
    DocumentBuilder builder;
    Document document;

    public ImportUserBean() throws ParserConfigurationException, SAXException, IOException {
        this.domFactory = DocumentBuilderFactory.newInstance();
        this.builder = domFactory.newDocumentBuilder();
        this.document = builder.parse("D:\\user.xml");
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
        NodeList nodeList = document.getElementsByTagName("user");
        NodeList fields = null;
        User user = new User();
        boolean er = false;
        jdbcUser tmpUser = new jdbcUser();
        ArrayList<User> arrayUser = new jdbcUser().get();
        for (int s = 0; s < nodeList.getLength(); s++) {
            Node node = nodeList.item(s);
            er = false;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element fstElmnt = (Element) node;
                fields = fstElmnt.getElementsByTagName("name");
                user.setName(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("middleName");
                user.setMiddleName(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("lastName");
                user.setLastName(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("type");
                user.setType(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("login");
                user.setLogin(getValue(fields, 0));
                fields = fstElmnt.getElementsByTagName("pass");
                user.setPass(getValue(fields, 0));
            }
            for (User us: arrayUser){
                if((us.getName().equals(user.getName()))&&(us.getMiddleName().equals(user.getMiddleName()))&&(us.getLastName().equals(user.getLastName()))&&(us.getType().equals(user.getType()))&&(us.getLogin().equals(user.getLogin()))&&(us.getPass().equals(user.getPass()))){
                    er = true;
                }
            }
            if(!er){
                tmpUser.add(user);
            }
        }
    }
}
