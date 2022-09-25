package pluginprovider.listeners;

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

    @EventHandler (priority = EventPriority.LOW)
    public void onEntityDie(org.bukkit.event.entity.EntityDeathEvent e) {
        EntityDamageByEntityEvent damage;
        try {
            damage = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
        } catch (ClassCastException r) {
            return;
        }
        if (damage == null) return;
        Player player = (Player) damage.getDamager();
        List<ItemStack> drops = new ArrayList<>(e.getDrops());
        e.getDrops().clear();
        EventInfo info = EventInfo.parseEventInfo(e.getEntity(), new Factors(e.getEntity(), player), drops);
        MainSystem.entityDropRequest(info);
    }

}
