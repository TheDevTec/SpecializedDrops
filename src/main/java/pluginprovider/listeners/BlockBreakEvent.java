package pluginprovider.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import pluginprovider.enums.ProvidedBlockDropType;
import pluginprovider.selectionsystems.MainSystem;
import pluginprovider.utils.AdvancedTypes;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEvent implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void breakBlock(org.bukkit.event.block.BlockBreakEvent e) {
        if (e.isCancelled()) return;
        e.setDropItems(false);
        List<ItemStack> defaultDrops = new ArrayList<>(e.getBlock().getDrops(e.getPlayer().getItemInUse()));
        ProvidedBlockDropType blockType = AdvancedTypes.getByMaterial(e.getBlock().getType());
        World world = e.getBlock().getWorld();
        Location loc = e.getBlock().getLocation();
        MainSystem.dropRequest(world, loc, blockType, e, defaultDrops);
    }

}
