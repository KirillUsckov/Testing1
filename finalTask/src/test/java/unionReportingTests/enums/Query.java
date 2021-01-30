package unionReportingTests.enums;

public enum Query {
    GET_TOKEN("/token/get"),
    GET_TESTS_LIST_IN_JSON("/test/get/json");
    private String query;

    Query(String s) {
        query = s;
    }

    public String getQuery() {
        return query;
    }
}
