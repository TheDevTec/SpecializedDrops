package pluginprovider.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class BlockBreakEvent implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void breakBlock(org.bukkit.event.block.BlockBreakEvent e) {
        if (!e.isDropItems()) return;
    }

}
