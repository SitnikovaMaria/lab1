package com.company;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Xml {

    Xml() {
        try {

            File file = new File("MyXMLFile.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = null;
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            }

            Document doc = null;
            try {
                doc = db.parse(file);
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            doc.getDocumentElement().normalize();

            System.out.println("Root element " + doc.getDocumentElement().getNodeName());

            NodeList nodeLst = doc.getElementsByTagName("employee");

            System.out.println("Information of all employees");


            for (int s = 0; s < nodeLst.getLength(); s++) {


                Node fstNode = nodeLst.item(s);


                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element fstElmnt = (Element) fstNode;

                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");

                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);

                    NodeList fstNm = fstNmElmnt.getChildNodes();

                    System.out.println("First Name : " + ((Node) fstNm.item(0)).getNodeValue());

                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");

                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);

                    NodeList lstNm = lstNmElmnt.getChildNodes();

                    System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());

                }


            }

        } catch (Exception e)

        {

            e.printStackTrace();

        }
    }
}
