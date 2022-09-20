package pluginprovider.enums;

public enum ProvidedEntityDropType {

    MOB_TYPE("Mob_Types"),
    FRIENDLY_TYPE("Friendly_Types");

    ProvidedEntityDropType(String dataPath) {
        this.dataPath = dataPath;
    }
    private final String dataPath;
    public String getDataPath() {
        return dataPath;
    }

}
