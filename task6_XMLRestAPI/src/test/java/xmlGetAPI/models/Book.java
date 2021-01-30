package xmlGetAPI.models;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Book {

    private Element nodeBook;
    private String title, author;
    private int id;
    private double price;

    public Book(Node node, int id) {
        nodeBook = (Element) node;
        this.id = id;
        setData();
    }

    private void setData() {
        NodeList props = nodeBook.getElementsByTagName("title");
        title = props.item(0).getTextContent();
        props = nodeBook.getElementsByTagName("author");
        author = props.item(0).getTextContent();
        props = nodeBook.getElementsByTagName("price");
        price = Double.parseDouble(props.item(0).getTextContent());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }


}

