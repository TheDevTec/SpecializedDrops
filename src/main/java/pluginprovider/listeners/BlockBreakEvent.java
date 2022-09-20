package pluginprovider.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Factors;
import pluginprovider.selectionsystems.MainSystem;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEvent implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void breakBlock(org.bukkit.event.block.BlockBreakEvent e) {
        if (e.isCancelled()) return;
        e.setDropItems(false);
        List<ItemStack> defaultDrops = new ArrayList<>(e.getBlock().getDrops(e.getPlayer().getItemInUse()));
        EventInfo info = EventInfo.parseEventInfo(e.getBlock(), new Factors(e), defaultDrops);
        MainSystem.blockDropRequest(info);
    }

}
