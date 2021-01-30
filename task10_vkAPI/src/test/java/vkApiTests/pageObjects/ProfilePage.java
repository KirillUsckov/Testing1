package vkApiTests.pageObjects;

import elements.Button;
import elements.Label;
import logger.Logger;
import pages.BasePage;
import vkApiTests.vkApi.models.VkApiComment;
import vkApiTests.vkApi.models.VkApiPost;

public class ProfilePage extends BasePage {
    private static final Logger LOG = new Logger(ProfilePage.class);

    private static final String DIV_POST_XPATH = "//div[@id='post";
    private static final String POST_TEXT_XPATH = "//div[@class='wall_post_text zoom_text']";
    private static final String AUTHOR_XPATH = "//a[@class='author']";
    private static final String DIV_REPLIES_XPATH = "//div[@id='replies_wrap";
    private static final String OPEN_REPLIES_LABEL_XPATH = "//a[@class='replies_next  replies_next_main']";

    private static final String DIV_LIKE_CONTAINS = "//div[contains(@class,'_like_wall";
    private static final String LIKE_ICON_XPATH = "//a[contains(@class,'_like')]//div[@class='like_button_icon']";

    private static final String POST_FORMAT = DIV_POST_XPATH + "%s_%s']";
    private static final String REPLIED_FORMAT = DIV_REPLIES_XPATH + "%s_%s']" + OPEN_REPLIES_LABEL_XPATH;
    private static final String LIKE_FORMAT = DIV_LIKE_CONTAINS +"%s_%s')]" + LIKE_ICON_XPATH;

    public ProfilePage(String locator) {
        super(locator);
    }

    public boolean isPostTextCorrect(VkApiPost post, String expectedText) {
        LOG.info("Инициализация переменной, содержащей текст поста");
        String postText = getPostText(post);
        LOG.info("Проверка корректности текста поста");
        return expectedText.equals(postText);
    }

    public String getPostText(VkApiPost post) {
        String userId = post.getOwnerId();
        String postId = post.getPostId();
        String postXPath = String.format(POST_FORMAT, userId, postId) + POST_TEXT_XPATH;
        LOG.info("Инициализация label — текст поста");
        Label postText = new Label(postXPath);

        LOG.info("Получение значения label");
        return postText.getElementText();
    }

    public boolean isCommentAuthorCorrect(VkApiComment comment, String expectedAuthor) {
        LOG.info("Инициализация переменной, содержащей автора поста");

        String commentAuthr = getCommentAuthor(comment);
        LOG.info("Проверка соответствия автора поста");
        return commentAuthr.equals(expectedAuthor);
    }

    private String getCommentAuthor(VkApiComment comment) {
        LOG.info("Инициализация id пользователя и поста, id комментария");
        String userId = comment.getUserId();
        String commentId = comment.getId();
        String postId = comment.getPostId();

        String commentXPath = String.format(POST_FORMAT, userId, commentId) + AUTHOR_XPATH;
        LOG.info("Инициализация label — текст поста");
        Label openCommentLabel = new Label(String.format(REPLIED_FORMAT, userId, postId));
        openCommentLabel.clickElement();
        Label commentAuthor = new Label(commentXPath);
        String result = commentAuthor.getElementAttributeValue("href");

        return result.substring(result.indexOf("id") + "id".length());
    }

    public void clickOnLike(VkApiPost post) {
        LOG.info("Инициализация label — текст поста");
        String userId = post.getOwnerId();
        String postId = post.getPostId();
        String likeXpath = String.format(LIKE_FORMAT, userId, postId);
        Button postLikeButton = new Button(likeXpath);
        postLikeButton.clickElement();
    }

}
