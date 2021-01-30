package vkApiTests.vkApi.models;

import apiUtils.JSONUtils;
import enums.VkApiObjTypes;
import io.restassured.response.Response;
import logger.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class VkApiPost {
    private static final Logger LOG = new Logger(VkApiPost.class);
    private ArrayList<String> postData = new ArrayList<>();
    private HashMap<String, String> post = new HashMap<>();
    private String postId;
    private VkApiLike like;

    public static VkApiPost createPost() {
        return new VkApiPost();
    }

    public VkApiPost setOwnerId(String id){
        LOG.info("Установка id владельца");
        postData.add("owner_id=" + id);
        post.put("owner_id", id);
        return this;
    }

    public VkApiPost setMessage(String message) {
        LOG.info("Установка сообщения");
        postData.add("message=" + message);
        post.put("message", message);
        return this;
    }

    public VkApiPost setCommentsStatus(String commentsStatus){
        LOG.info("Установка статуса комментариев");
        postData.add("close_comments=" + commentsStatus);
        post.put("close_comments", commentsStatus);
        return this;
    }

    public VkApiPost setUserId(String userId) {
        LOG.info("Установка id пользователя");
        post.put("user_id", userId);
        return this;
    }

    public void setPostId(Response response) {
        LOG.info("Установка id поста");
        String body = response.getBody().asString();
        postId = String.valueOf(JSONUtils.getJSONValue(body, "response"));
        postId = String.valueOf(JSONUtils.getJSONValue(postId, "post_id"));
    }

    public void setPostId(String postId) {
        LOG.info("Установка id поста");
        postData.add("post_id=" + postId);
    }

    public void setPostDataForEditText(String text) {
        postData.remove("message");
        postData.add("message=" + text);
        post.replace("message", text);
        postData.add("post_id=" + postId);
    }

    public void setPostDataForDelete() {
        postData.removeAll(postData);
        setOwnerId(post.get("owner_id"));
        setPostId(postId);
    }

    public void setLikeData(){
        like = VkApiLike.create()
                .setType(VkApiObjTypes.POST)
                .setOwnerId(post.get("owner_id"))
                .setItemId(postId)
                .setUserId(post.get("user_id"));
    }

    public String getPostData() {
        return VkApiBaseModel.getData(postData);
    }

    public String getPostId() {
        return postId;
    }

    public String getOwnerId() {
        return post.get("owner_id");

    }

    public VkApiLike getLikeData() {
        return like;
    }
}
