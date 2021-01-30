package vkApiTests.vkApi.models;

import apiUtils.APIUtils;

import constants.VkApiConstants;
import enums.APIResponses;
import enums.VkApiObjTypes;
import io.restassured.response.Response;
import logger.Logger;
import vkApiTests.vkApi.VkApiUtils;
import vkApiTests.enums.vkApi.Like;

import java.util.ArrayList;

public class VkApiLike {
    private static final Logger LOG = new Logger(VkApiLike.class);
    private ArrayList<String> likeData = new ArrayList<>();

    public static VkApiLike create() {
        return new VkApiLike();
    }


    public VkApiLike setType(VkApiObjTypes type) {
        LOG.info("Установка типа объекта для лайка");
        likeData.add("type=" + type.name().toLowerCase());
        return this;
    }

    public VkApiLike setUserId(String userId) {
        LOG.info("Установка id пользователя");
        likeData.add("user_id=" + userId);
        return this;
    }

    public VkApiLike setItemId(String id) {
        LOG.info("Установка id элемента");
        likeData.add("item_id=" + id);
        return this;
    }

    public VkApiLike setOwnerId(String ownerId) {
        LOG.info("Установка id владельца");
        likeData.add("owner_id=" + ownerId);
        return this;
    }

    public String getLikeData() {
        return VkApiBaseModel.getData(likeData);
    }

    public void add() {
        LOG.info("Формирование запроса для POST-запроса");
        String request = VkApiConstants.REQUEST_START + Like.ADD_LIKE.getMethod() + getLikeData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
    }

}
