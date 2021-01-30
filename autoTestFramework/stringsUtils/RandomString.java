package stringsUtils;

import logger.Logger;

import java.util.Random;

public class RandomString {
    private static Logger logger;
    private static char[] latinChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPGRSTUVWXYZ".toCharArray();
    private static char[] cyrillicChars = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toCharArray();

    public static String getRandomLatinString(int length) {
        logger = new Logger(RandomString.class);

        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        logger.info("Generate random text");
        for (int i = 0; i < length; i++) {
            char c = latinChars[random.nextInt(latinChars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomCyrillicString(int length) {
        logger = new Logger(RandomString.class);

        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        logger.info("Generate random text");
        for (int i = 0; i < length; i++) {
            char c = cyrillicChars[random.nextInt(cyrillicChars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomStringFromText(int length, String text) {
        logger = new Logger(RandomString.class);
        Random random = new Random();
        logger.info("Random char selection");
        int startIndex = random.nextInt(length);
        int endIndex = startIndex + length;
        return text.substring(startIndex,endIndex);
    }
}
