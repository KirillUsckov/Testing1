package vkApiTests.steps;

import org.testng.Assert;
import stringsUtils.RandomString;
import vkApiTests.pageObjects.ProfilePage;
import vkApiTests.vkApi.VkApiUtils;
import vkApiTests.vkApi.models.VkApiComment;
import vkApiTests.vkApi.models.VkApiPhoto;
import vkApiTests.vkApi.models.VkApiPost;

public class ApiSteps {
    private String userId, text, postId;
    private VkApiPost post;
    private VkApiUtils api;
    private ProfilePage profilePage;
    private VkApiComment comment;

    public ApiSteps(ProfilePage profilePage, VkApiUtils api) {
        this.api = api;
        this.profilePage = profilePage;
    }

    public VkApiPost getPost() {
        return post;
    }

    public VkApiComment getComment() {
        return comment;
    }

    public void addPost() {
        profilePage.waitingForOpen();
        userId = profilePage.getUrl().substring(profilePage.getUrl().indexOf("id") + "id".length());
        text = RandomString.getRandomLatinString(30);
        post = VkApiPost.createPost()
                .setOwnerId(userId)
                .setMessage(text)
                .setCommentsStatus("0")
                .setUserId(userId);
        api.addPost(post);
        postId = post.getPostId();
        Assert.assertTrue(profilePage.isPostTextCorrect(post, text));
    }

    public void addRandomTextAndPhotoToPost(String photoPath) {
        text = RandomString.getRandomLatinString(20);
        editPostText(text);
        //addPhotoToPost(photoPath);
    }

    public void editPostText(String message) {
        api.editPostText(post, message);
    }

    public void addPhotoToPost(String photoPath) {
        VkApiPhoto photo = new VkApiPhoto(photoPath);
        api.addPhotoToTheWall(photo, userId);
    }

    public void addRandomCommentToPost() {
        text = RandomString.getRandomLatinString(20);
        addCommentToPost(text);
        Assert.assertTrue(profilePage.isCommentAuthorCorrect(comment, userId));
    }

    public void addCommentToPost(String message) {
        comment = VkApiComment.create(text)
                .setOwnerId(post.getOwnerId())
                .setPostId(postId);
        api.addCommentToPost(comment);
    }


}
