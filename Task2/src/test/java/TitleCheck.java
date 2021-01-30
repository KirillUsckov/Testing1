/**
 * Класс предназначен для обработки исключетиельных ситуаций:
 *  - Пункт меню, имеющий большую длину, не совпадает с заголовком страницы,
 *  на которую он переводит (например, у заголовков отличаются склонения слов)
 *  - Пункт меню не совпадает с заголовком страницы, на которую он переводит
 *  (в меню - "Зоо", а заголовок на странице "Товары для животных"
 */

public class TitleCheck {

    public static String getExpectedTitle(String title) {
        if (title.contains("Зоо")){
            return "Товары для животных";
        } else if (title.contains("етям")){
            return "Детские товары";
        } else if (title.contains("Дом")){
            return "Товары для дома";
        } else {
            return title.substring(1, title.length() - 1);
        }
    }


    public static String getExpectedTitleForMarket(String title) {
        if (title.length() > 5) {
            title = title.substring(1, title.length() - 1);
        }
        return title;
    }
}
