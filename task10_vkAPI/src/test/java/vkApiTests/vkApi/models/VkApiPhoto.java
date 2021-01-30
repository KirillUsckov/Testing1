package vkApiTests.vkApi.models;

import apiUtils.APIUtils;
import apiUtils.JSONUtils;
import constants.VkApiConstants;
import enums.APIResponses;
import io.restassured.response.Response;
import logger.Logger;
import vkApiTests.vkApi.VkApiUtils;


import java.util.ArrayList;

public class VkApiPhoto {
    private static final Logger LOG = new Logger(VkApiPhoto.class);
    private String photoPath;
    private String uploadServer;
    private ArrayList<String> photoData = new ArrayList<>();

    public VkApiPhoto(String path) {
        LOG.info("Инициализация фото, установка пути к фото");
        photoPath = path;
    }

    public void setAlbumId(String albumId) {
        LOG.info("Установка id альбома");
        photoData.add("album_id=" + albumId);
    }

    public void setUserId(String userId) {
        LOG.info("Установка id пользователя");
        photoData.add("user_id=" + userId);
    }

    public void setPhoto(String photo) {
        LOG.info("Установка данных фото");
        photoData.add("photo=" + photo);
    }

    public void setOwnerId(String ownerId) {
        LOG.info("Установка id владельца");
        photoData.add("owner_id=" + ownerId);
    }

    public void setPhotosList(String photosList) {
        LOG.info("Установка данных списка фото");
        photoData.add("photos_list=" + photosList);
    }

    public void setServer(String server) {
        LOG.info("Установка сервера");
        photoData.add("server=" + server);
    }

    public void setHash(String hash) {
        LOG.info("Установка хеша");
        photoData.add("hash=" + hash);
    }

    public void setUploadServer(String serverPath) {
        LOG.info("Формирование POST-запроса");
        String request = VkApiConstants.REQUEST_START + serverPath + getPhotoData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);

        LOG.info("Установка адреса сервера");
        String body = response.getBody().asString();
        body = String.valueOf(JSONUtils.getJSONValue(body, "response"));
        uploadServer = String.valueOf(JSONUtils.getJSONValue(body, "upload_url"));
    }

    public void setDataForUpload(String userId) {
        photoData.removeAll(photoData);
        setUserId(userId);
    }

    public String getUploadServer() {
        return uploadServer;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getPhotoData() {
        return VkApiBaseModel.getData(photoData);
    }




}
