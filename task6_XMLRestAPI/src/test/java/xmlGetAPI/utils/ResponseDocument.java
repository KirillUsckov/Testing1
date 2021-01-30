package xmlGetAPI.utils;

import logger.Logger;
import org.w3c.dom.Document;
import xmlGetAPI.constants.CommonConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;

public class ResponseDocument {
    private Logger logger;
    private Document doc = null;

    public ResponseDocument() {
        logger = new Logger(ResponseDocument.class);
    }

    public Document create(String respnseText) {
        try {
            FileWriter writer = new FileWriter(CommonConstants.XML_TEXT_FILE, true);
            writer.append(respnseText);
            writer.flush();
            writer.close();

            DocumentBuilderFactory dbf;
            DocumentBuilder db;

            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(CommonConstants.XML_TEXT_FILE));
        } catch (Exception e) {
            logger.error(e);
        }
        return doc;
    }

    public boolean delete() {
        try {
            File file = new File(CommonConstants.XML_TEXT_FILE);
            file.delete();
            return true;
        } catch (Exception e){
            logger.error(e);
            return false;
        }
    }

}
