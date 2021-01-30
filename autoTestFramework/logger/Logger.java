package logger;

import constants.CommonConstants;
import stringsUtils.PropertiesReader;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private FileWriter logWriter;
    private String className = "";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private String date;
    private int stepAmount = 1;

    public Logger(Class nameOfClass) {
        try {
            className = nameOfClass.toString().replaceAll("class", "");
            String filePath = PropertiesReader.getPropertyFromProperties(CommonConstants.PROPERTIES_FILE_PATH_TEXT);
            logWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger() {
        try {
            String filePath = PropertiesReader.getPropertyFromProperties(CommonConstants.PROPERTIES_FILE_PATH_TEXT);
            logWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(Object message) {
        try {
            date = dateFormat.format(new Date());
            String text = "INFO\t" + date + " " + className + ":\t" + message.toString() + "\n";
            logWriter.write(text);
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(Object message) {
        try {
            date = dateFormat.format(new Date());
            String text = "ERROR\t" + date + " " + className + ":\t" + message.toString() + "\n";
            logWriter.write(text);
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void step(Object message) {
        try {
            date = dateFormat.format(new Date());
            String text = "\nSTEP " + stepAmount + "\t" + date + ":\t" + message.toString() + "\n";
            logWriter.write(text);
            logWriter.flush();
            stepAmount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testCase() {
        try {
            date = dateFormat.format(new Date());
            String text = "\nTestCase " + "\t" + date + ":\t" + className + "\n";
            logWriter.write(text);
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        logWriter.close();
    }


}
