package pluginprovider.utils;

public enum WriterResult {

    SUCCESS("Data has been successfully written!"),
    INVALID_FILE("Selected file is null or invalid!"),
    UNEXPECTED_ERROR("Something happened... Try it later!");

    WriterResult(String formatted) {
        this.formatted = formatted;
    }
    private final String formatted;
    public String getFormatted() {
        return formatted;
    }

}
