package xmlGetAPI.models;

import logger.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class BooksList {
    private static Logger logger = new Logger(BooksList.class);

    public static boolean isMinAndMaxPriceBooksDifferent(ArrayList<Book> booksList) {
        logger.info("Initialisation of maxPrice, minPrice, maxPriceBook and minPriceBook variables");
        double maxPrice = 0;
        double minPrice = 0;
        String maxPriceBookTitle = "";
        String minPriceBookTitle = "";

        for (Book el: booksList) {
            logger.info("Find book's price");
            double price = el.getPrice();

            logger.info("Checking that book's price is max or min");
            if (maxPrice < price) {
                maxPrice = price;
                maxPriceBookTitle = el.getTitle();
            } else if (booksList.indexOf(el) == 0 || minPrice > price) {
                minPrice = price;
                minPriceBookTitle = el.getTitle();
            }
        }
        logger.info("Return result of checking");
        return !maxPriceBookTitle.equals(minPriceBookTitle);
    }

    public static boolean isBooksListSorted(ArrayList<Book> booksList) {
        int issues = 0;
        logger.info("Create sortedBooksId list and fill it with books' id");
        ArrayList<Integer> sortedBooksId = new ArrayList<>();
        for (Book el: booksList) {
            sortedBooksId.add(el.getId());
        }

        logger.info("Sort sortedBooksId list");
        Collections.sort(sortedBooksId);

        logger.info("Checking that original books' id list is equal to the sorted list");
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getId() != sortedBooksId.get(i)) {
                issues ++;
            }
        }
        return issues == 0;
    }

}
