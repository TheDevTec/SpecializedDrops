package pluginprovider.objects;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import pluginprovider.enums.ProvidedBlockDropType;
import pluginprovider.enums.ProvidedEntityDropType;
import pluginprovider.utils.AdvancedTypes;

import java.util.List;

public class EventInfo {

    // Builder
    public static EventInfo parseEventInfo(Block block, Factors factors, List<ItemStack> defaultDrops) {
        return new EventInfo(AdvancedTypes.getByMaterial(block.getType()), factors, block.getLocation(), block.getWorld(), defaultDrops);
    }
    public static EventInfo parseEventInfo(Entity killed, Factors factors, List<ItemStack> defaultDrops) {
        return new EventInfo(AdvancedTypes.getByEntityType(killed.getType()), factors, killed.getLocation(), killed.getWorld(), defaultDrops);
    }

    // Values
    private final ProvidedBlockDropType providedBlockDropType;
    private final ProvidedEntityDropType providedEntityDropType;
    private final List<ItemStack> defaultDrop;
    private final Factors factors;
    private final Location location;
    private final World world;

    // Private constructor
    private EventInfo(ProvidedBlockDropType providedBlockDropType, Factors factors, Location loc, World world, List<ItemStack> defaultDrop) {
        this.factors = factors;
        this.providedBlockDropType = providedBlockDropType;
        this.providedEntityDropType = null;
        this.location = loc;
        this.world = world;
        this.defaultDrop = defaultDrop;
    }
    private EventInfo(ProvidedEntityDropType providedBlockDropType, Factors factors, Location loc, World world, List<ItemStack> defaultDrop) {
        this.factors = factors;
        this.providedBlockDropType = null;
        this.providedEntityDropType = providedBlockDropType;
        this.location = loc;
        this.world = world;
        this.defaultDrop = defaultDrop;
    }

    // Getter
    public Factors getFactors() {
        return factors;
    }
    public Object getCorrect() {
        if (providedEntityDropType == null) return providedBlockDropType;
        else return providedEntityDropType;
    }
    public World getWorld() {
        return world;
    }
    public Location getLocation() {
        return location;
    }
    public List<ItemStack> getDefaultDrop() {
        return defaultDrop;
    }

}
