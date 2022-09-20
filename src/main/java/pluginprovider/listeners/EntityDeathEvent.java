package pluginprovider.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Factors;
import pluginprovider.selectionsystems.MainSystem;

import java.util.*;

public class EntityDeathEvent implements Listener {

    private static final Map<UUID, UUID> player_entity = new HashMap<>();

    @EventHandler (priority = EventPriority.LOW)
    public void onEntityDie(org.bukkit.event.entity.EntityDeathEvent e) {
        if (player_entity.containsKey(e.getEntity().getUniqueId())) {
            Player player = Bukkit.getPlayer(player_entity.get(e.getEntity().getUniqueId()));
            assert player != null;
            List<ItemStack> drops = new ArrayList<>(e.getDrops());
            e.getDrops().clear();
            EventInfo info = EventInfo.parseEventInfo(e.getEntity(), new Factors(e.getEntity(), player), drops);
            MainSystem.entityDropRequest(info);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onEntityHitByPlayer(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) {
            player_entity.put(e.getEntity().getUniqueId(), e.getDamager().getUniqueId());
        }
    }

}
