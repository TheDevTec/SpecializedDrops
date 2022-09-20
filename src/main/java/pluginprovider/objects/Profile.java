package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import pluginprovider.enums.factors.FactorsDataType;
import pluginprovider.enums.factors.valuetypes.FactorsLookingValueType;
import pluginprovider.enums.factors.valuetypes.FactorsTimeValueType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Values
    private final Map<FactorsDataType, Object> values;

    // Constructor
    public Profile(String fileName) {
        Config quickConfig = Config.loadFromFile("/plugins/SpecializedDrops/Overrides/Profiles/" + fileName);
        values = new HashMap<>();
        // Time
        FactorsDataType time = FactorsDataType.TIME;
        String time_constructor = quickConfig.getString("Conditions.Time");
        FactorsLookingValueType time_value = FactorsLookingValueType.valueOf(time_constructor);
        // Permissions
        FactorsDataType permissions = FactorsDataType.PERMISSION;
        List<String> permissions_value = quickConfig.getStringList("Conditions.Permissions");
        // World
        FactorsDataType world = FactorsDataType.WORLD;
        String world_value = quickConfig.getString("Conditions.World");
        // Block/Entity
        FactorsDataType block_entity = FactorsDataType.BLOCK_ENTITY;
        List<String> block_entity_value = quickConfig.getStringList("Conditions.Types");
        // Sneaking
        FactorsDataType sneaking = FactorsDataType.SNEAKING;
        boolean sneaking_value = quickConfig.getBoolean("Conditions.Sneaking");
        // Biome
        FactorsDataType biome = FactorsDataType.BIOME;
        String biome_value = quickConfig.getString("Conditions.Biome");
        // Raining
        FactorsDataType raining = FactorsDataType.RAINING;
        boolean raining_value = quickConfig.getBoolean("Conditions.Raining");
        // Raining
        FactorsDataType destroyer_killer = FactorsDataType.KILLED_DESTROYED_BY;
        List<String> destroyer_killer_value = quickConfig.getStringList("Conditions.Destroyer");
        // Fortune/Looking
        FactorsDataType fortune_looting = FactorsDataType.FORTUNE_LOOTING;
        boolean fortune_looting_value = quickConfig.getBoolean("Conditions.Luck");
        // Looking
        FactorsDataType looking = FactorsDataType.LOOKING;
        String looking_constructor = quickConfig.getString("Conditions.Looking");
        FactorsLookingValueType looking_value = FactorsLookingValueType.valueOf(looking_constructor);
        //
        // Register
        //
        values.put(time, time_value);
        values.put(permissions, permissions_value);
        values.put(world, world_value);
        values.put(block_entity, block_entity_value);
        values.put(sneaking, sneaking_value);
        values.put(biome, biome_value);
        values.put(raining, raining_value);
        values.put(destroyer_killer, destroyer_killer_value);
        values.put(fortune_looting, fortune_looting_value);
        values.put(looking, looking_value);
    }

    // Functions
    public boolean asyncCheckWith(EventInfo info) {
        boolean returner = true;
        // Time
        if (values.get(FactorsDataType.TIME) != null) {
            if (info.getFactors().getTimeValue() != (FactorsTimeValueType) values.get(FactorsDataType.TIME))
                returner = false;
        }
        // Permission
        if (values.get(FactorsDataType.PERMISSION) != null) {
            if (!info.getFactors().getPermissionsValue()) returner = false;
        }
        // World
        if (values.get(FactorsDataType.WORLD) != null) {
            if (!((String) values.get(FactorsDataType.WORLD)).equalsIgnoreCase(info.getFactors().getWorldValue()))
                returner = false;
        }
        // Block / Entity
        if (values.get(FactorsDataType.BLOCK_ENTITY) != null) {
            if (info.getFactors().getEntityValue() != null) {
                if (!info.getFactors().getEntityValue().name().equalsIgnoreCase((String) values.get(FactorsDataType.BLOCK_ENTITY)))
                    returner = false;
            }
            if (info.getFactors().getBlockValue() != null) {
                if (!info.getFactors().getBlockValue().name().equalsIgnoreCase((String) values.get(FactorsDataType.BLOCK_ENTITY)))
                    returner = false;
            }
        }
        // Sneaking
        if (values.get(FactorsDataType.SNEAKING) != null) {
            if (info.getFactors().getSneakingValue() != (Boolean) values.get(FactorsDataType.SNEAKING)) returner = false;
        }
        // Biome
        if (values.get(FactorsDataType.BIOME) != null) {
            if (!info.getFactors().getBiomeValue().equalsIgnoreCase((String) values.get(FactorsDataType.BIOME)))
                returner = false;
        }
        // Raining
        if (values.get(FactorsDataType.RAINING) != null) {
            if (info.getFactors().getRainingValue() != (Boolean) values.get(FactorsDataType.RAINING)) returner = false;
        }
        // Killed/Destroyed by
        if (values.get(FactorsDataType.KILLED_DESTROYED_BY) != null) {
            if (!info.getFactors().getDestroyerOrKillerValue().equalsIgnoreCase((String) values.get(FactorsDataType.KILLED_DESTROYED_BY)))
                returner = false;
        }
        // Fortune/Looting
        if (values.get(FactorsDataType.FORTUNE_LOOTING) != null) {
            if (info.getFactors().getFortuneLootingValue() != (Boolean) values.get(FactorsDataType.FORTUNE_LOOTING))
                returner = false;
        }
        // Looking
        if (values.get(FactorsDataType.LOOKING) != null) {
            if (info.getFactors().getLookingValue() != (FactorsLookingValueType) values.get(FactorsDataType.LOOKING))
                returner = false;
        }
        return returner;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}