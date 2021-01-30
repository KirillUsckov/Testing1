package xmlGetAPI.models;

import logger.Logger;
import xmlGetAPI.utils.ResponseDocument;

import io.restassured.response.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.util.ArrayList;
import java.util.Collections;

public class BooksInitialization {

    private static final String BOOK_ID_XPATH = "//catalog/book[@id]";

    private static final String CATALOG_TEXT = "catalog";
    private static final String BOOK_TEXT = "book";
    private static final String ID_TEXT = "id";
    private static final String BK_TEXT = "bk";
    private static final int BK_TEXT_LENGTH = BK_TEXT.length();
    private String responseText;

    private Document doc;
    private ResponseDocument responseDocument;

    private ArrayList<Book> booksList = new ArrayList<>();

    private Logger logger;

    public BooksInitialization(Response response) {
        logger = new Logger(BooksInitialization.class);
        logger.info("Initialisation of booksList");

        responseText = response.getBody().asString();

        responseText = responseText.substring("<?xml version='1.0'?>".length());
        responseText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + responseText;

        responseDocument = new ResponseDocument();
        doc = responseDocument.create(responseText);
    }


    public ArrayList<Book> createBooksList() {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expBook = xpath.compile(BOOK_ID_XPATH);

            Element responseData = doc.getDocumentElement();

            if (CATALOG_TEXT.equals(responseData.getTagName())) {
                //информация тела тега book
                NodeList booksInfo = responseData.getElementsByTagName(BOOK_TEXT);
                //вся ифнормация из документа, нужно, чтобы найти значение id
                NodeList books = (NodeList) expBook.evaluate(doc, XPathConstants.NODESET);

                for (int i = 0; i < booksInfo.getLength(); ++i) {
                    String id = books.item(i).getAttributes().getNamedItem(ID_TEXT).getNodeValue();
                    id = id.substring(id.indexOf(BK_TEXT) + BK_TEXT_LENGTH);
                    booksList.add(new Book(booksInfo.item(i), Integer.parseInt(id)));
                }
            }
            return booksList;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }


    public void deleteAllBooks() {
        logger.info("Deleting of response's result document");
        responseDocument.delete();
    }

}
