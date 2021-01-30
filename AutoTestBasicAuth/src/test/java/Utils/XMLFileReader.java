package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * StringsReader class is used for read xml files
 */

public class XMLFileReader {
    private String pathToXMLFile;
    private String customElement;
    private NodeList strings;

    /**
     * Initialising Utils.XMLFileReader.
     * @param xmlFilePath
     */
    public XMLFileReader(String xmlFilePath) {
        pathToXMLFile = xmlFilePath;
        getStringFromXML();
    }

    /**
     * Reading all parameters from XML file.
     */
    private void getStringFromXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(this.pathToXMLFile);
            Element root = document.getDocumentElement();
            strings = root.getChildNodes();
        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    /**
     * Receiving required parameter.
     * @param elementName
     * @return
     */
    public String getCustomElement(String elementName) {
        this.setCustomElement(strings, elementName);
        return customElement;
    }

    /**
     * Finding required element in list.
     * @param nodeList
     * @param name
     */
    private void setCustomElement(NodeList nodeList, String name) {
        for(int i = 0; i < nodeList.getLength(); ++i) {
            if (nodeList.item(i) instanceof Element) {
                setCustomString((Element)nodeList.item(i), name);
                if (nodeList.item(i).hasChildNodes()) {
                    setCustomElement(nodeList.item(i).getChildNodes(), name);
                }
            }
        }
    }

    /**
     * Setting required element.
     * @param systemPrmtr
     * @param name
     */
    private void setCustomString(Element systemPrmtr, String name) {
        if (systemPrmtr.getTagName().contains(name)) {
            customElement = systemPrmtr.getTextContent();
        }
    }
}
