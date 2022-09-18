package pluginprovider.enums;

public enum ProvidedBlockDropType {

    UNDEFINED_TYPE("Undefined_Types"),
    GLASS_TYPE("Glass_Types"),
    ADVANTAGE_TYPE("Advantage_Types"),
    BASED_BLOCK_TYPE("Based_Block_Types"),
    DESIGN_TYPE("Design_Types"),
    USING_TYPE("Using_Types"),
    ELECTRICITY_TYPE("Electricity_Types"),
    PLANTS_TYPE("Plants_Types"),
    SNOW_TYPE("Snow_Types"),
    DIRT_TYPE("Dirt_Types"),
    SAND_TYPE("Sand_Types"),
    STONE_TYPE("Stone_Types"),
    DEEP_STONE_TYPE("Deep_Stone_Types"),
    ORE_TYPE("Ore_Types"),
    WOOD_TYPE("Wood_Types"),
    FOOD_TYPE("Food_Types");

    ProvidedBlockDropType(String dataPath) {
        this.dataPath = dataPath;
    }
    private final String dataPath;
    public String getDataPath() {
        return dataPath;
    }

}
