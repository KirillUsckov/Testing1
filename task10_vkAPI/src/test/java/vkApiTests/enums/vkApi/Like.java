package vkApiTests.enums.vkApi;

public enum Like {
    ADD_LIKE("likes.add"),
    IS_LIKED("likes.isLiked");
    private String method;

    Like(String s) {
        method = s;
    }

    public String getMethod() {
        return method;
    }

}
