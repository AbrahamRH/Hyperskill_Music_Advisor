package advisor;

public enum Parameters {
    CLIENT_ID("bdc901d3a3e549e9bed37099fdba57ae"),
    CLIENT_SECRET("c1b3247c0e66496882586dbc4f6ef89d"),
    ACCESS("https://accounts.spotify.com"),
    RESOURCE("https://api.spotify.com"),

    PAGE("5"),
    AUTH_CODE(""),
    TOKEN("");
    private String value;

    Parameters(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
