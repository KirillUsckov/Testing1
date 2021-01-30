package vkApiTests.vkApi;

import apiUtils.APIUtils;
import apiUtils.JSONUtils;
import constants.CommonConstants;
import constants.VkApiConstants;
import enums.APIResponses;
import io.restassured.response.Response;
import logger.Logger;
import stringsUtils.XMLStringsReader;
import vkApiTests.enums.vkApi.Comment;
import vkApiTests.enums.vkApi.Like;
import vkApiTests.enums.vkApi.Photo;
import vkApiTests.enums.vkApi.Post;
import vkApiTests.vkApi.models.*;

public class VkApiUtils {
    private static final Logger LOG = new Logger(VkApiUtils.class);

    private static String userToken;

    public VkApiUtils(String token){
        userToken = token;
    }

    public void addPost(VkApiPost post) {
        LOG.info("Формирование POST-запроса");
        String request = VkApiConstants.REQUEST_START + Post.ADD_POST.getMethod() + post.getPostData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
        post.setPostId(response);
    }

    public void addLikeToPost(VkApiLike like) {
        LOG.info("Формирование запроса для POST-запроса");
        String request = VkApiConstants.REQUEST_START + Like.ADD_LIKE.getMethod() + like.getLikeData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
    }

    public void addCommentToPost(VkApiComment comment) {
        LOG.info("Формирование запроса на добалвние комментария");
        String request = VkApiConstants.REQUEST_START + Comment.CREATE_COMMENT.getMethod() + comment.getCommentData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка запроса на добавление комментария");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
        comment.setCommentId(response);
    }

    public void addPhotoToTheWall(VkApiPhoto photo, String ownerId) {
        photo.setOwnerId(ownerId);
        uploadPhotoToWall(photo, ownerId);
        savePhotoToWall(photo);
    }

    public void addPhotoToTheAlbum(VkApiPhoto photo, String ownerId) {
        photo.setOwnerId(ownerId);
        uploadPhotoToAlbum(photo, ownerId);
        saveToAlbum(photo);
    }

    public void uploadPhotoToWall(VkApiPhoto photo, String userId) {
        photo.setUploadServer(Photo.UPLOAD_WALL_SERVER_PATH.getMethod());

        photo.setDataForUpload(userId);

        LOG.info("Отправка POST-запроса на сервер для загрузки фото");
        Response response = APIUtils.postWithParam(photo.getUploadServer(), "photo", photo.getPhotoPath());
        APIUtils.checkResponse(response, LOG);
        String resp = response.getBody().asString();

        LOG.info("Установка параметров: photo, server, hash");
        String ph = String.valueOf(JSONUtils.getJSONValue(resp, "photo"));
        photo.setPhoto(ph);
        String server = String.valueOf(JSONUtils.getJSONValue(resp, "server"));
        photo.setServer(server);
        String hash = String.valueOf(JSONUtils.getJSONValue(resp, "hash"));
        photo.setHash(hash);
    }

    public void savePhotoToWall(VkApiPhoto photo) {
        LOG.info("Формирование запроса для POST-запроса");
        String request = VkApiConstants.REQUEST_START + Photo.SAVE_ON_WALL.getMethod() + photo.getPhotoData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
    }

    public void uploadPhotoToAlbum(VkApiPhoto photo, String photoNum) {
        photo.setUploadServer(Photo.UPLOAD_SERVER_PATH.getMethod());

        LOG.info("Формирование POST-запроса");
        Response response = APIUtils.postWithParam(photo.getUploadServer(), "file" + photoNum,
                                                                                    photo.getPhotoPath());
        String resp = response.getBody().asString();

        String server = String.valueOf(JSONUtils.getJSONValue(resp, "server"));
        photo.setServer(server);
        String photosList = String.valueOf(JSONUtils.getJSONValue(resp, "photos_list"));
        photo.setPhotosList(photosList);
        String hash = String.valueOf(JSONUtils.getJSONValue(resp, "hash"));
        photo.setHash(hash);
    }

    public void saveToAlbum(VkApiPhoto photo) {
        String request = VkApiConstants.REQUEST_START + Photo.SAVE_PHOTO.getMethod() + photo.getPhotoData();
        request = VkApiUtils.getCorrectRequest(request);
        Response response = APIUtils.query(APIResponses.POST, request);
    }

    public boolean isPostLiked(VkApiPost post) {
        post.setLikeData();
        VkApiLike like = post.getLikeData();
        LOG.info("Формирование запроса для POST-запроса для проверки наличия лайка");
        String request = VkApiConstants.REQUEST_START + Like.IS_LIKED.getMethod() + like.getLikeData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка запроса для POST-запроса для проверки наличия лайка");
        Response response = APIUtils.get(request);
        APIUtils.checkResponse(response, LOG);

        LOG.info("Проверка кода наличия лайка");
        String body = response.getBody().asString();
        body = String.valueOf(JSONUtils.getJSONValue(body, "response"));
        int likeCode = Integer.parseInt(String.valueOf(JSONUtils.getJSONValue(body, "liked")));
        return likeCode == 1;
    }


    public void editPostText(VkApiPost post, String text) {
        post.setPostDataForEditText(text);

        LOG.info("Формирование POST-запроса");
        String request = VkApiConstants.REQUEST_START + Post.EDIT_POST.getMethod() + post.getPostData();
        request = VkApiUtils.getCorrectRequest(request);

        LOG.info("Отправка POST-запроса");
        Response response = APIUtils.query(APIResponses.POST, request);
        APIUtils.checkResponse(response, LOG);
    }


    public static String getCorrectRequest(String request) {
        String apiVer = new XMLStringsReader(CommonConstants.SETTINGS_PATH)
                                            .getCustomElement("apiVer");
        return request + "&access_token=" + userToken + "&v=" + apiVer;
    }

    public void deletePost(VkApiPost post) {
        post.setPostDataForDelete();

        String request = VkApiConstants.REQUEST_START + Post.DELETE_POST.getMethod() + post.getPostData();
        request = VkApiUtils.getCorrectRequest(request);
        Response response = APIUtils.delete(request);
        APIUtils.checkResponse(response, LOG);
    }

}
