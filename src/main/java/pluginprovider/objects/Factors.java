package pluginprovider.objects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pluginprovider.enums.factors.FactorsDataType;
import pluginprovider.enums.factors.valuetypes.FactorsLookingValueType;
import pluginprovider.enums.factors.valuetypes.FactorsTimeValueType;

import java.util.HashMap;
import java.util.Map;

public class Factors {

    private final Map<FactorsDataType, Object> dataType_dataValue;
    private final boolean isBlock;

    public Factors(Block block, Player p, String... permissions) {
        isBlock = true;
        dataType_dataValue = new HashMap<>();
        // Time
        FactorsTimeValueType day_night;
        if (block.getWorld().getTime() < 12300 | block.getWorld().getTime() > 23850) day_night = FactorsTimeValueType.DAY;
        else day_night = FactorsTimeValueType.NIGHT;
        // Permission
        boolean permission = true;
        for (String var : permissions) {
            if (!p.hasPermission(var)) permission = false;
        }
        // World
        String worldName = block.getWorld().getName();
        // -> Block / !-> Entity
        Material blockType = block.getType();
        // Sneaking
        boolean isSneaking = p.isSneaking();
        // Biome name
        String biomeName = block.getBiome().name();
        // Raining
        boolean isRaining = block.getWorld().hasStorm();
        // Killed / Destroyed by
        String playerName = p.getName();
        // !-> Looting / -> Fortune
        boolean canCheck = p.getItemInUse() != null;
        boolean hasFortune;
        if (canCheck) hasFortune = p.getItemInUse().getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS);
        else hasFortune = false;
        // Looking
        FactorsLookingValueType up_down = FactorsLookingValueType.STRAIGHT;
        if (p.getLocation().getPitch() < -30) up_down = FactorsLookingValueType.UP;
        if (p.getLocation().getPitch() > 30) up_down = FactorsLookingValueType.DOWN;
        //
        // Register
        //
        dataType_dataValue.put(FactorsDataType.TIME, day_night);
        dataType_dataValue.put(FactorsDataType.PERMISSION, permission);
        dataType_dataValue.put(FactorsDataType.WORLD, worldName);
        dataType_dataValue.put(FactorsDataType.BLOCK_ENTITY, blockType);
        dataType_dataValue.put(FactorsDataType.SNEAKING, isSneaking);
        dataType_dataValue.put(FactorsDataType.BIOME, biomeName);
        dataType_dataValue.put(FactorsDataType.RAINING, isRaining);
        dataType_dataValue.put(FactorsDataType.KILLED_DESTROYED_BY, playerName);
        dataType_dataValue.put(FactorsDataType.FORTUNE_LOOTING, hasFortune);
        dataType_dataValue.put(FactorsDataType.LOOKING, up_down);
    }
    public Factors(Entity entity, Player p, String... permissions) {
        isBlock = true;
        dataType_dataValue = new HashMap<>();
        // Time
        FactorsTimeValueType day_night;
        if (entity.getWorld().getTime() < 12300 | entity.getWorld().getTime() > 23850) day_night = FactorsTimeValueType.DAY;
        else day_night = FactorsTimeValueType.NIGHT;
        // Permission
        boolean permission = true;
        for (String var : permissions) {
            if (!entity.hasPermission(var)) permission = false;
        }
        // World
        String worldName = entity.getWorld().getName();
        // -> Block / !-> Entity
        EntityType blockType = entity.getType();
        // Sneaking
        boolean isSneaking = p.isSneaking();
        // Biome name
        String biomeName = entity.getLocation().getBlock().getBiome().name();
        // Raining
        boolean isRaining = entity.getWorld().hasStorm();
        // Killed / Destroyed by
        String playerName = p.getName();
        // !-> Looting / -> Fortune
        boolean canCheck = p.getItemInUse() != null;
        boolean hasFortune;
        if (canCheck) hasFortune = p.getItemInUse().getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS);
        else hasFortune = false;
        // Looking
        FactorsLookingValueType up_down = FactorsLookingValueType.STRAIGHT;
        if (p.getLocation().getPitch() < -30) up_down = FactorsLookingValueType.UP;
        if (p.getLocation().getPitch() > 30) up_down = FactorsLookingValueType.DOWN;
        //
        // Register
        //
        dataType_dataValue.put(FactorsDataType.TIME, day_night);
        dataType_dataValue.put(FactorsDataType.PERMISSION, permission);
        dataType_dataValue.put(FactorsDataType.WORLD, worldName);
        dataType_dataValue.put(FactorsDataType.BLOCK_ENTITY, blockType);
        dataType_dataValue.put(FactorsDataType.SNEAKING, isSneaking);
        dataType_dataValue.put(FactorsDataType.BIOME, biomeName);
        dataType_dataValue.put(FactorsDataType.RAINING, isRaining);
        dataType_dataValue.put(FactorsDataType.KILLED_DESTROYED_BY, playerName);
        dataType_dataValue.put(FactorsDataType.FORTUNE_LOOTING, hasFortune);
        dataType_dataValue.put(FactorsDataType.LOOKING, up_down);
    }

    // Getters
    public boolean isBlock() {
        return isBlock;
    }
    public FactorsTimeValueType getTimeValue() {
        return (FactorsTimeValueType) dataType_dataValue.get(FactorsDataType.TIME);
    }
    public boolean getPermissionsValue() {
        return (boolean) dataType_dataValue.get(FactorsDataType.PERMISSION);
    }
    public String getWorldValue() {
        return (String) dataType_dataValue.get(FactorsDataType.WORLD);
    }
    public Material getBlockValue() {
        if (isBlock) return (Material) dataType_dataValue.get(FactorsDataType.BLOCK_ENTITY);
        else return null;
    }
    public EntityType getEntityValue() {
        if (!isBlock) return (EntityType) dataType_dataValue.get(FactorsDataType.BLOCK_ENTITY);
        else return null;
    }
    public boolean getSneakingValue() {
        return (boolean) dataType_dataValue.get(FactorsDataType.SNEAKING);
    }
    public String getBiomeValue() {
        return (String) dataType_dataValue.get(FactorsDataType.BIOME);
    }
    public boolean getRainingValue() {
        return (boolean) dataType_dataValue.get(FactorsDataType.RAINING);
    }
    public String getDestroyerOrKillerValue() {
        return (String) dataType_dataValue.get(FactorsDataType.KILLED_DESTROYED_BY);
    }
    public boolean getFortuneLootingValue() {
        return (boolean) dataType_dataValue.get(FactorsDataType.FORTUNE_LOOTING);
    }
    public FactorsLookingValueType getLookingValue() {
        return (FactorsLookingValueType) dataType_dataValue.get(FactorsDataType.LOOKING);
    }

}