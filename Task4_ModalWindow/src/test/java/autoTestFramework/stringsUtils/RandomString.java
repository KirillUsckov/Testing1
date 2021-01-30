package autoTestFramework.stringsUtils;

import autoTestFramework.logger.Logger;

import java.util.Random;

public class RandomString {
    private static Logger logger;
    private static char[] chars = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPGRSTUVWXYZ".toCharArray();

    public static String getRandomString(int length) {
        logger = new Logger(RandomString.class);

        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        logger.info("Generate random text");
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
