package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.event.block.BlockBreakEvent;
import pluginprovider.enums.factors.FactorsDataType;
import pluginprovider.enums.factors.valuetypes.FactorsLookingValueType;
import pluginprovider.enums.factors.valuetypes.FactorsTimeValueType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Profile {

    private static final Map<String, Profile> cache = new HashMap<>();
    public static Profile getProfileByName(String name) {
        return cache.get(name);
    }
    public static Profile getRandomProfile() {
        return StringUtils.getRandomFromList(cache.values().parallelStream().collect(Collectors.toList()));
    }
    public static Collection<Profile> getProfiles() {
        return cache.values();
    }
    public static Collection<String> getProfilesNames() {
        return cache.keySet();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private final Map<FactorsDataType, Object> values;

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

    public boolean asyncCheckWith(final BlockBreakEvent e) {
        boolean returner = true;
        Factors destroyFactors = new Factors(e, ((List<String>) values.get(FactorsDataType.PERMISSION)).parallelStream().toArray(String[]::new));
        // Time
        if (values.get(FactorsDataType.TIME) != null) {
            if (destroyFactors.getTimeValue() != (FactorsTimeValueType) values.get(FactorsDataType.TIME))
                returner = false;
        }
        // Permission
        if (values.get(FactorsDataType.PERMISSION) != null) {
            if (!destroyFactors.getPermissionsValue()) returner = false;
        }
        // World
        if (values.get(FactorsDataType.WORLD) != null) {
            if (!((String) values.get(FactorsDataType.WORLD)).equalsIgnoreCase(destroyFactors.getWorldValue()))
                returner = false;
        }
        // Block / Entity
        if (values.get(FactorsDataType.BLOCK_ENTITY) != null) {
            if (destroyFactors.getEntityValue() != null) {
                if (!destroyFactors.getEntityValue().name().equalsIgnoreCase((String) values.get(FactorsDataType.BLOCK_ENTITY)))
                    returner = false;
            }
            if (destroyFactors.getBlockValue() != null) {
                if (!destroyFactors.getBlockValue().name().equalsIgnoreCase((String) values.get(FactorsDataType.BLOCK_ENTITY)))
                    returner = false;
            }
        }
        // Sneaking
        if (values.get(FactorsDataType.SNEAKING) != null) {
            if (destroyFactors.getSneakingValue() != (Boolean) values.get(FactorsDataType.SNEAKING)) returner = false;
        }
        // Biome
        if (values.get(FactorsDataType.BIOME) != null) {
            if (!destroyFactors.getBiomeValue().equalsIgnoreCase((String) values.get(FactorsDataType.BIOME)))
                returner = false;
        }
        // Raining
        if (values.get(FactorsDataType.RAINING) != null) {
            if (destroyFactors.getRainingValue() != (Boolean) values.get(FactorsDataType.RAINING)) returner = false;
        }
        // Killed/Destroyed by
        if (values.get(FactorsDataType.KILLED_DESTROYED_BY) != null) {
            if (!destroyFactors.getDestroyerOrKillerValue().equalsIgnoreCase((String) values.get(FactorsDataType.KILLED_DESTROYED_BY)))
                returner = false;
        }
        // Fortune/Looting
        if (values.get(FactorsDataType.FORTUNE_LOOTING) != null) {
            if (destroyFactors.getFortuneLootingValue() != (Boolean) values.get(FactorsDataType.FORTUNE_LOOTING))
                returner = false;
        }
        // Looking
        if (values.get(FactorsDataType.LOOKING) != null) {
            if (destroyFactors.getLookingValue() != (FactorsLookingValueType) values.get(FactorsDataType.LOOKING))
                returner = false;
        }
        return returner;
    }

}