package iframe.projectUtils;

import java.util.Random;

public class RandomString {

    public static String getRandomString(int length) {
        char[] chars = " abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPGRSTUVWXYZ ".toCharArray();
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
