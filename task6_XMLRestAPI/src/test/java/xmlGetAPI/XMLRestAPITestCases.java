package xmlGetAPI;

import apiUtils.APIQuery;
import baseTest.BaseAPITest;

import io.restassured.response.Response;
import logger.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import xmlGetAPI.models.Book;
import xmlGetAPI.models.BooksList;
import xmlGetAPI.models.BooksInitialization;

import java.util.ArrayList;

public class XMLRestAPITestCases extends BaseAPITest {
    private Logger logger;
    private BooksInitialization books;
    private ArrayList<Book> booksList;

    @BeforeClass
    public void initialisation() {
        logger = new Logger(XMLRestAPITestCases.class);
    }

    @Test
    public void getTests() {
        logger.step("Sending GET query");
        logger.info("Sending query");
        Response getBooksResponse = APIQuery.get(siteUrl);

        logger.info("Checking response status code");
        Assert.assertEquals(getBooksResponse.getStatusCode(), 200);
        books = new BooksInitialization(getBooksResponse);
        booksList = books.createBooksList();

        logger.info("Checking that books list is sorted");
        Assert.assertTrue(BooksList.isBooksListSorted(booksList));

        logger.step("Searching for books with max and min prices");
        Assert.assertTrue(BooksList.isMinAndMaxPriceBooksDifferent(booksList));
    }

    @AfterTest
    public void delete() {
        books.deleteAllBooks();
    }
}
