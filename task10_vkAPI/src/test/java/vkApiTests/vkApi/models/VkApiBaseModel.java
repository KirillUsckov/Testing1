package vkApiTests.vkApi.models;

import logger.Logger;

import java.util.ArrayList;

public class VkApiBaseModel {
    private static final Logger LOG = new Logger(VkApiBaseModel.class);

    public static String getData(ArrayList<String> objData) {
        LOG.info("Создание строки с данными объекта для запроса");
        String result = "";
        for (String el: objData) {
            if (objData.indexOf(el) == 0) {
                result += "?" + el;
            } else {
                result += "&" + el;
            }
        }
        LOG.info("Возврат строки с данными объекта для запроса");
        return result;
    }

}
