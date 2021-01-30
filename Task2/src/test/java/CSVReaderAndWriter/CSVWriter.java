package CSVReaderAndWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {

    private static final char DEFAULT__SEPARATOR = '-';

    public static void writeDocument(List<WebElement> list,String csvFile) {

        try {
            createFile(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csvFile));
            ArrayList<String> menusTitles = new ArrayList<>();
            for (WebElement element: list) {
                menusTitles.add(element.findElement(By.tagName("span")).getText());
                writeDocumentLine(bufferedWriter, element.findElement(By.tagName("span")).getText(), DEFAULT__SEPARATOR);
            }
                bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(String csvPath){
        try {
            // Возьмите файл
            File csvFile = new File(csvPath);
            //Создайте новый файл
            // Убедитесь, что он не существует
            if (csvFile.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void writeDocumentLine(Writer writer, String value, char separators) throws IOException {
        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT__SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        if (!first) {
            sb.append(separators);
        }
        sb.append(value);
        sb.append("\n");
        writer.append(sb.toString());
    }

}
