package CSVReaderAndWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс предназначен для чтения и последующей записи всех строк csv файла
 */

public class CSVReader {

    public static ArrayList<String> readDocument(String url) {
        ArrayList<String> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(url))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[]values = line.split("/");
                for (String el: values){
                    records.add(el);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

}
