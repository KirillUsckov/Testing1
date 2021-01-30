package vkApiTests.vkApi.models;

import apiUtils.JSONUtils;
import io.restassured.response.Response;
import logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class VkApiComment {
    private static final Logger LOG = new Logger(VkApiComment.class);
    private String commentText;
    private ArrayList<String> commentData = new ArrayList<>();
    private HashMap<String, String> comment = new HashMap<>();
    private String commentId;

    private VkApiComment(String text) {
        LOG.info("Инициализация комментария");
        commentText = text;
        setMessage();
    }

    public static VkApiComment create(String text) {
        return new VkApiComment(text);
    }

    public VkApiComment setOwnerId(String ownerId) {
        LOG.info("Установка id владельца");
        commentData.add("owner_id=" + ownerId);
        comment.put("owner_id", ownerId);
        return this;
    }

    public VkApiComment setPostId(String postId) {
        LOG.info("Установка id поста");
        commentData.add("post_id=" + postId);
        comment.put("post_id", postId);
        return this;
    }

    private VkApiComment setMessage() {
        LOG.info("Установка сообщения");
        commentData.add("message=" + commentText);
        return this;
    }

    public void setCommentId(Response response) {
        LOG.info("Установка id кооментария");
        String body = response.getBody().asString();
        String comId = String.valueOf(JSONUtils.getJSONValue(body, "response"));
        commentId = String.valueOf(JSONUtils.getJSONValue(comId, "comment_id"));
    }

    public String getCommentData() {
        LOG.info("Получение текста комментария");
        return VkApiBaseModel.getData(commentData);
    }

    public String getPostId() {
        return comment.get("post_id");
    }

    public String getId() {
        LOG.info("Получение id комментария");
        return commentId;
    }

    public String getUserId() {
        return comment.get("owner_id");
    }


}
