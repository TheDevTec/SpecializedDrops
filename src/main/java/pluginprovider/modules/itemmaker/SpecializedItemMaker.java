package pluginprovider.modules.itemmaker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pluginprovider.SpecializedDrops;

import java.util.*;

public class SpecializedItemMaker implements Listener {

    // Values
    private final Map<UUID, CreatorsGUI> cache;
    private final Set<String> titles;

    // Constructor
    public SpecializedItemMaker() {
        this.cache = new HashMap<>();
        this.titles = new HashSet<>(Arrays.asList("&8➥ ItemCreator", "&8➥ Material Picker", "&8➥ Criteria"));
        Bukkit.getPluginManager().registerEvents(this, SpecializedDrops.getInstance());
    }

    // Functions
    public void runFor(Player p) {
        if (cache.containsKey(p.getUniqueId())) {
            cache.get(p.getUniqueId()).update();
        } else {
            cache.put(p.getUniqueId(), CreatorsGUI.parseGUI(p));
            cache.get(p.getUniqueId()).update();
        }
    }

}
