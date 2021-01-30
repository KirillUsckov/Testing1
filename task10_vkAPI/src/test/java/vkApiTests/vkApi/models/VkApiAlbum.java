package vkApiTests.vkApi.models;

import apiUtils.APIUtils;
import apiUtils.JSONUtils;
import constants.VkApiConstants;
import enums.APIResponses;
import io.restassured.response.Response;
import logger.Logger;
import vkApiTests.vkApi.VkApiUtils;
import vkApiTests.enums.vkApi.Album;

import java.util.ArrayList;
import java.util.HashMap;

public class VkApiAlbum {
    private static final Logger LOG = new Logger(VkApiAlbum.class);
    private ArrayList<String> albumData = new ArrayList<>();
    private HashMap<String, String> album = new HashMap<>();
    private String id;

    public static VkApiAlbum create() {
        return new VkApiAlbum();
    }

    public VkApiAlbum setTitle(String title) {
        LOG.info("Установка заголовка альбома");
        albumData.add("title=" + title);
        return this;
    }

    public VkApiAlbum setOwnerId(String ownerId) {
        LOG.info("Установка id владельца");
        albumData.add("owner_id=" + ownerId);
        album.put("owner_id", ownerId);
        return this;
    }

    private void setId(Response response) {
        LOG.info("Установка id");
        String body = response.getBody().asString();
        body = String.valueOf(JSONUtils.getJSONValue(body, "response"));
        id = String.valueOf(String.valueOf(JSONUtils.getJSONValue(body, "id")));
    }

    public void addAlbum() {
        LOG.info("Формирование запроса на добавление альбома");
        String request = VkApiConstants.REQUEST_START + Album.CREATE_ALBUM.getMethod() + VkApiBaseModel.getData(albumData);
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка запроса на добавление альбома");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);

        setId(response);
    }

}
