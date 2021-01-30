package Reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

public class StringsReader {
    private static StringsReader stringsReader;

    private final String PATH_TO_STRINGS = "src/main/resources/strings.xml";
    private String siteUrl, browser, propertyDirectoryPath, email, password;

    private int codeOS;

    private StringsReader(){
    }

    public static StringsReader getInstance() {
        if (stringsReader == null) {
            stringsReader = new StringsReader();
        }
        return stringsReader;
    }


    public void getStringFromXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(PATH_TO_STRINGS);
            Element root = document.getDocumentElement();

            NodeList strings = root.getChildNodes();
            setElements(strings);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setElements(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                setStrings(((Element) nodeList.item(i)));
                if (nodeList.item(i).hasChildNodes()) {
                    setElements(nodeList.item(i).getChildNodes());
                }
            }
        }
    }

    private void setStrings(Element systemPrmtr) {
        switch (systemPrmtr.getTagName()) {
            case "siteUrl":
                siteUrl = systemPrmtr.getTextContent();
                break;
            case "browser":
                browser = systemPrmtr.getTextContent();
                break;
            case "PropertyDirectoryPath":
                propertyDirectoryPath = systemPrmtr.getTextContent();
                break;
            case "userEmail":
                email = systemPrmtr.getTextContent();
                break;
            case "userPassword":
                password = systemPrmtr.getTextContent();
                break;
            case "codeOS":
                codeOS = Integer.parseInt(systemPrmtr.getTextContent());
                break;

        }
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public String getPropertyDirectoryPath(){
        return propertyDirectoryPath;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getCodeOS() {
        return codeOS;
    }
}


