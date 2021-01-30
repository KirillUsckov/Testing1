package getPostTests.enums;

public enum APIResources {
    ALL_POSTS("/posts"),
    CUSTOM_POST("/posts/"),
    ALL_USERS("/users");

    private String s;
    APIResources(String s) {
        this.s = s;
    }

    public String get(){ return s;}
}
