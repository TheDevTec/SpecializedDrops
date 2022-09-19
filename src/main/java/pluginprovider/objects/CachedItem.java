package pluginprovider.objects;

import org.bukkit.inventory.ItemStack;
import pluginprovider.utils.Processor;

public class CachedItem {

    private final String pathToItem;
    private final double chance;

    public CachedItem(String pathToItem, double chance) {
        this.pathToItem = pathToItem;
        this.chance = chance;
    }

    // Complex actions
    public ItemStack asyncBuildStack() {
        return Processor.readItem(new CachedAttributes(pathToItem));
    }
    public ItemStack asyncBuildAndExecute(Factors factors) {
        return Processor.readAndExecute(new CachedAttributes(pathToItem), factors);
    }
    public CachedAttributes quickAttributes() {
        return new CachedAttributes(pathToItem);
    }

    // Simple getters
    public double getChance() {
        return chance;
    }

}
