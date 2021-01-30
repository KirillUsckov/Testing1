package enums;

public enum AuthData {
    USERNAME,
    PASSWORD,
    TRUSERNAME,
    TRPASSWORD,
    DBUSERNAME,
    DBPASSWORD;

    private String name;

    AuthData() {
        name = this.name().toLowerCase();
    }

    public String get() {
        return name;
    }
}
