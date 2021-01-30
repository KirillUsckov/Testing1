package apiUtils;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONUtils {
    private static int issues = 0;

    private static String[] jsonArray;

    private static ArrayList<Integer> jsonList = new ArrayList<>();
    private static ArrayList<Integer> sortedJSONList;

    public static boolean isJSON(String text) {
        return text.indexOf("{") == 0 && text.lastIndexOf("}") == (text.length() - 1);
    }

    public static String createJSONString(String[] jsonData) {
        String json = "{";
        for (int i = 0; i < jsonData.length; i++) {
            String el = jsonData[i];
            String key = el.substring(0, el.indexOf(" "));
            String value = el.substring(el.indexOf(" ") + 1);
            json += "\n\t\"" + key + "\"" + ":" + "\"" + value + "\"";
            if (i < jsonData.length - 1) {
                json += ",";
            }
        }
        json += "\n}";
        return json;
    }

    public static String getCorrectJSON(Response response) {
        String result = response.asString();

        return result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
    }

    public static boolean isJSONFileSorted(String key, String json) {
        jsonArray = json.split("},\n");
        for (String el: jsonArray) {
            JSONObject object = new JSONObject(el + "}");
            jsonList.add(object.getInt(key));
        }
        sortedJSONList = jsonList;
        Collections.sort(sortedJSONList);

        for (int i = 0; i < jsonList.size(); i++) {
            if (sortedJSONList.get(i) != jsonList.get(i)) {
                issues++;
            }
        }
        return issues == 0;
    }

    public static boolean checkArrayValues(ArrayList<String> arrayList, String json) {
        boolean result = true;
        String key,value;

        for (String el: arrayList) {
            key = el.substring(0, el.indexOf(" "));
            value = el.substring(el.indexOf(" ") + 1);
            if (value.equals("null")) {
                result = result && JSONUtils.isKeyValueNull(json, key);
            } else if (value.equals("notNull")) {
                result = result && !JSONUtils.isKeyValueNull(json, key);
            } else {
                result = result && JSONUtils.checkKeyValue(json, key, value);
            }
        }
        return result;
    }

    public static List<String> getJSONList(String json, String key) {
        List<String> list = new ArrayList<>();
        if (json.contains("[") || json.contains("]")) {
            json = json.replace("[", "").replace("]", "");
        }
        String [] tests = json.split("},");
        for (String el: tests) {
            el += "}";
            JSONObject obj = new JSONObject(el);
            list.add(obj.getString(key));
        }
        return list;
    }

    public static boolean checkKeyValue(String json, String key, String value) {
        JSONObject object = new JSONObject(json);
        if (key.contains("id") || key.contains("Id")) {
            int jsonValue = Integer.parseInt(String.valueOf(object.get(key)));
            return jsonValue == Integer.parseInt(value);
        }
        if (value.contains("{")) {
            JSONObject geo = new JSONObject(value);
            return object.get(key).toString().equals(geo.toString());
        }
        return object.get(key).equals(value);
    }

    public static boolean isKeyValueNull(String json, String key) {
        JSONObject object = new JSONObject(json);
        return object.get(key).toString().length() == 0;
    }

    public static boolean isBodyOfJSONEmpty(String json) {
        return json.length() == 2; //т.к. в пустом ответе будут только фигурные скобки
    }

}
