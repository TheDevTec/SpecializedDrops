package pluginprovider.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityDeathEvent implements Listener {

    private static final Map<UUID, UUID> player_entity = new HashMap<>();

    @EventHandler (priority = EventPriority.LOW)
    public void onEntityDie(org.bukkit.event.entity.EntityDeathEvent e) {

    }

    @EventHandler (priority = EventPriority.LOW)
    public void onEntityHitByPlayer(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) {
            player_entity.put(e.getDamager().getUniqueId(), e.getEntity().getUniqueId());
        }
    }

}
