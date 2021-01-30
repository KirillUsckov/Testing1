package vkApiTests.enums.vkApi;

public enum Photo {
    UPLOAD_SERVER_PATH("photos.getUploadServer"),
    UPLOAD_WALL_SERVER_PATH("photos.getWallUploadServer"),
    SAVE_ON_WALL("photos.saveWallPhoto"),
    SAVE_PHOTO("photos.save");

    private String method;

    Photo(String s) {
        method = s;
    }

    public String getMethod() {
        return method;
    }
}
