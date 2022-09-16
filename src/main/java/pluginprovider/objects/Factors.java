package pluginprovider.objects;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import pluginprovider.enums.factors.FactorsDataType;
import pluginprovider.enums.factors.valuetypes.FactorsLookingValueType;
import pluginprovider.enums.factors.valuetypes.FactorsTimeValueType;

import java.util.HashMap;
import java.util.Map;

public class Factors {

    private final Map<FactorsDataType, Object> dataType_dataValue;
    private final boolean isBlock;

    public Factors(final BlockBreakEvent e, String... permissions) {
        isBlock = true;
        dataType_dataValue = new HashMap<>();
        // Time
        FactorsTimeValueType day_night;
        if (e.getBlock().getWorld().getTime() < 12300 | e.getBlock().getWorld().getTime() > 23850) day_night = FactorsTimeValueType.DAY;
        else day_night = FactorsTimeValueType.NIGHT;
        // Permission
        boolean permission = true;
        for (String var : permissions) {
            if (!e.getPlayer().hasPermission(var)) permission = false;
        }
        // World
        String worldName = e.getBlock().getWorld().getName();
        // -> Block / !-> Entity
        Material blockType = e.getBlock().getType();
        // Sneaking
        boolean isSneaking = e.getPlayer().isSneaking();
        // Biome name
        String biomeName = e.getBlock().getBiome().name();
        // Raining
        boolean isRaining = e.getBlock().getWorld().hasStorm();
        // Killed / Destroyed by
        String playerName = e.getPlayer().getName();
        // !-> Looting / -> Fortune
        boolean canCheck = e.getPlayer().getItemInUse() != null;
        boolean hasFortune;
        if (canCheck) hasFortune = e.getPlayer().getItemInUse().getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS);
        else hasFortune = false;
        // Looking
        FactorsLookingValueType up_down = FactorsLookingValueType.STRAIGHT;
        if (e.getPlayer().getLocation().getPitch() < -30) up_down = FactorsLookingValueType.UP;
        if (e.getPlayer().getLocation().getPitch() > 30) up_down = FactorsLookingValueType.DOWN;
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
    public Factors(final EntityDeathEvent e, Player killer, String... permissions) {
        isBlock = true;
        dataType_dataValue = new HashMap<>();
        // Time
        FactorsTimeValueType day_night;
        if (e.getEntity().getWorld().getTime() < 12300 | e.getEntity().getWorld().getTime() > 23850) day_night = FactorsTimeValueType.DAY;
        else day_night = FactorsTimeValueType.NIGHT;
        // Permission
        boolean permission = true;
        for (String var : permissions) {
            if (!e.getEntity().hasPermission(var)) permission = false;
        }
        // World
        String worldName = e.getEntity().getWorld().getName();
        // -> Block / !-> Entity
        EntityType blockType = e.getEntity().getType();
        // Sneaking
        boolean isSneaking = killer.isSneaking();
        // Biome name
        String biomeName = e.getEntity().getLocation().getBlock().getBiome().name();
        // Raining
        boolean isRaining = e.getEntity().getWorld().hasStorm();
        // Killed / Destroyed by
        String playerName = killer.getName();
        // !-> Looting / -> Fortune
        boolean canCheck = killer.getItemInUse() != null;
        boolean hasFortune;
        if (canCheck) hasFortune = killer.getItemInUse().getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS);
        else hasFortune = false;
        // Looking
        FactorsLookingValueType up_down = FactorsLookingValueType.STRAIGHT;
        if (killer.getLocation().getPitch() < -30) up_down = FactorsLookingValueType.UP;
        if (killer.getLocation().getPitch() > 30) up_down = FactorsLookingValueType.DOWN;
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
