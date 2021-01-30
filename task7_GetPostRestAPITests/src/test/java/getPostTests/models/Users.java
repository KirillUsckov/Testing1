package getPostTests.models;

import apiUtils.APIUtils;
import apiUtils.JSONUtils;
import getPostTests.enums.APIResources;
import io.restassured.response.Response;
import logger.Logger;

import java.util.ArrayList;

public class Users {
    private final static String ADDRESS_TEXT = "\"address\":";
    private final static String COMPANY_TEXT = "\"company\":";

    private Response response;
    private String url;
    private ArrayList<String> usersList = new ArrayList<>();
    private Logger logger;

    public Users(String siteUrl) {
        logger = new Logger(Users.class);
        logger.info("Initialisation of variables");
        url = siteUrl;
    }

    public String getAllUsers() {
        logger.info("Send GET query to get all users");
        response = APIUtils.get(url + APIResources.ALL_USERS.get());
        fillUsersList();
        logger.info("Return GET response body text with all users");
        return JSONUtils.getCorrectJSON(response);
    }

    public String getUserById(String id) {
        logger.info("Send GET query to specific user");
        response = APIUtils.get(url + APIResources.ALL_USERS.get() + "/" + id);
        logger.info("Return GET response body text with 1 user");
        return JSONUtils.getCorrectJSON(response);
    }

    public boolean responseCodeChecking(int code) {
        logger.info("Return result of checking response's code");
        return response.getStatusCode() == code;
    }

    private void fillUsersList() {
        try {
            String regex = "},\n";
            logger.info("Initialisation of usersList and filling the list");
            String[] array = JSONUtils.getCorrectJSON(response).split(regex);

            for (int i = 0; i < array.length; i += 2) {
                if (array[i].contains("id")) {
                    usersList.add(array[i] + regex + array[i + 1] + "}");
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public String findUserById(String id) {
        try {
            logger.info("Searching for usr with required id");
            for (String el : usersList) {
                if (JSONUtils.checkKeyValue(el, "id", id)) {
                    return el;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public boolean checkUserData(String json, ArrayList<String> commonData,
                                    ArrayList<String> addressData, ArrayList<String> companyData) {
        try {
            logger.info("Initialisation of address data");
            String address = json.substring(json.indexOf(ADDRESS_TEXT) + ADDRESS_TEXT.length(), json.indexOf("},")) + "\n}";
            logger.info("Initialisation of company data");
            String company = json.substring(json.indexOf(COMPANY_TEXT) + COMPANY_TEXT.length(), json.lastIndexOf("}"));
            return checkUserAddress(addressData, address) &&
                    checkUserCompany(companyData, company) &&
                    checkUserCommonData(commonData, json);
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    public boolean checkUserAddress(ArrayList<String> addressData, String address) {
        logger.info("Checking address' data");
        return JSONUtils.checkArrayValues(addressData, address);
    }

    public boolean checkUserCompany(ArrayList<String> companyData, String company) {
        logger.info("Checking company's data");
        return JSONUtils.checkArrayValues(companyData, company);
    }

    public boolean checkUserCommonData(ArrayList<String> commonData, String body) {
        logger.info("Checking common data");
        return JSONUtils.checkArrayValues(commonData, body);
    }
}
