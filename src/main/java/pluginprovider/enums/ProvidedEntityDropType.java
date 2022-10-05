package pluginprovider.enums;

import pluginprovider.utils.ProvidedDataPath;

public enum ProvidedEntityDropType implements ProvidedDataPath {

    CANCELED(""),
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
