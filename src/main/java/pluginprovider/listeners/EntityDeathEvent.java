package pluginprovider.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Factors;
import pluginprovider.selectionsystems.MainSystem;
import pluginprovider.utils.AdvancedTypes;

import java.util.ArrayList;
import java.util.List;

public class EntityDeathEvent implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onEntityDie(org.bukkit.event.entity.EntityDeathEvent e) {
        EntityDamageByEntityEvent damage;
        try {
            damage = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
        } catch (ClassCastException r) {
            return;
        }
        if (damage == null) return;
        if (damage.getDamager() instanceof Player) {
            Player player = (Player) damage.getDamager();
            if (!SpecializedDrops.supportedGameModes.contains(player.getGameMode().name())) {
                return;
            }
            List<ItemStack> drops = new ArrayList<>(e.getDrops());
            e.getDrops().clear();
            EventInfo info = EventInfo.parseEventInfo(player, e.getEntity().getLocation(), AdvancedTypes.getByEntityType(e.getEntity().getType()), new Factors(e.getEntity(), player), drops);
            MainSystem.dropRequest(info);
        }
    }

}
