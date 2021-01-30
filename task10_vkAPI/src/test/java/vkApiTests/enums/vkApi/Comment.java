package vkApiTests.enums.vkApi;

public enum Comment {
    CREATE_COMMENT("wall.createComment");
    private String method;

    Comment(String s) {
        method = s;
    }

    public String getMethod() {
        return method;
    }
}
