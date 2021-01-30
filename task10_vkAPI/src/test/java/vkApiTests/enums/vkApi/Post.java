package vkApiTests.enums.vkApi;

public enum Post {
    DELETE_POST("wall.delete"),
    ADD_POST("wall.post"),
    EDIT_POST("wall.edit");

    private String method;

    Post(String s) {
        method = s;
    }

    public String getMethod() {
        return method;
    }
}
