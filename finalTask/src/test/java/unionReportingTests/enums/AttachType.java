package unionReportingTests.enums;

public enum AttachType {
    TEXT("text/html"),
    IMG("image/png");

    private String type;

    AttachType(String attType) {
        type = attType;
    }

    public String get() {
        return type;
    }
}
