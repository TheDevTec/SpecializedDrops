package pluginprovider.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Factors;
import pluginprovider.selectionsystems.MainSystem;
import pluginprovider.utils.AdvancedTypes;

import java.util.Collection;

public class BlockBreakEvent implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void breakBlock(org.bukkit.event.block.BlockBreakEvent e) {
        if (e.isCancelled() || !e.isDropItems()) return;
        if (!SpecializedDrops.supportedGameModes.contains(e.getPlayer().getGameMode().name())) {
            return;
        }
        e.setDropItems(false);
        Collection<ItemStack> defaultDrops = e.getBlock().getDrops(e.getPlayer().getItemInUse());
        EventInfo info = EventInfo.parseEventInfo(e.getPlayer(), e.getBlock().getLocation(), AdvancedTypes.getByMaterial(e.getBlock().getType()), new Factors(e.getBlock(), e.getPlayer()), defaultDrops);
        MainSystem.dropRequest(info);
    }

}
