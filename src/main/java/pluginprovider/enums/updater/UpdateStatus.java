package pluginprovider.enums.updater;

public enum UpdateStatus {

    LATEST("&aYour SpecializedItems.jar is latest!"),
    NEED_TO_UPDATE("&cYour plugin need update!"),
    PIPE_ERROR("&4Internal error... Try it later!");

    UpdateStatus(String var) {
        this.var = var;
    }
    public final String var;
    public String getFormatted() {
        return var;
    }
    public static UpdateStatus parseUpdateStatus(String actualVersion, String latestVersion) {
        if (actualVersion == null || latestVersion == null) return UpdateStatus.PIPE_ERROR;
        if (actualVersion.equals(latestVersion)) return UpdateStatus.LATEST;
        else return UpdateStatus.NEED_TO_UPDATE;
    }

}