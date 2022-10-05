package pluginprovider.objects;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.utils.ProvidedDataPath;

import java.util.Collection;

public class EventInfo {

    // Builder
    public static EventInfo parseEventInfo(Player destroyer, Location location, ProvidedDataPath killed, Factors factors, Collection<ItemStack> defaultDrops) {
        return new EventInfo(destroyer, killed, factors, location, destroyer.getWorld(), defaultDrops);
    }

    // Values
    private final ProvidedDataPath providedBlockDropType;
    private final Collection<ItemStack> defaultDrop;
    private final Factors factors;
    private final Location location;
    private final World world;
    private final Player destroyer;

    // Private constructor
    private EventInfo(Player destroyer, ProvidedDataPath providedBlockDropType, Factors factors, Location loc, World world, Collection<ItemStack> defaultDrop) {
        this.factors = factors;
        this.providedBlockDropType = providedBlockDropType;
        this.location = loc;
        this.world = world;
        this.defaultDrop = defaultDrop;
        this.destroyer = destroyer;
    }

    // Getter
    public Factors getFactors() {
        return factors;
    }
    public ProvidedDataPath getCorrect() {
        return providedBlockDropType;
    }
    public World getWorld() {
        return world;
    }
    public Location getLocation() {
        return location;
    }
    public Collection<ItemStack> getDefaultDrop() {
        return defaultDrop;
    }
    public Player getDestroyer() {
        return destroyer;
    }

}
