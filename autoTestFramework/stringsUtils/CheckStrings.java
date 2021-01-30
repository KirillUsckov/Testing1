package stringsUtils;

/**
 * CheckStrings class is used for formatting strings
 */

public class CheckStrings {

    public static boolean checkValue(Object o1, Object o2) {
        return o1.equals(o2);
    }

    public static String getCorrectStringForDouble(String input, String oldString, String newString) {
        if (input.contains(oldString)) {
            input = input.replaceAll(oldString, newString);
        }
        return input;
    }

    public static String getIntFromString(String input, String oldString, String newString) {
        if (input.contains(oldString)) {
            input = input.substring(0, input.lastIndexOf(oldString));
            input = input.replaceAll(oldString, newString);
        }
        return input;
    }
}
