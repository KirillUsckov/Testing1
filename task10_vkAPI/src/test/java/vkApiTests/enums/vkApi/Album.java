package vkApiTests.enums.vkApi;

public enum Album {
    CREATE_ALBUM("photos.createAlbum");
    private String method;

    Album(String s) {
        method = s;
    }

    public String getMethod() {
        return method;
    }
}
