package pluginprovider.modules.itemmaker;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpecializedItemMaker {

    // Values
    private final Map<UUID, CreatorsGUI> cache;

    // Constructor
    public SpecializedItemMaker() {
        this.cache = new HashMap<>();
    }

    // Functions
    public void runFor(Player p) {
        if (cache.containsKey(p.getUniqueId())) {
            cache.get(p.getUniqueId()).openCreator();
        } else {
            cache.put(p.getUniqueId(), CreatorsGUI.parseGUI(p));
            cache.get(p.getUniqueId()).openCreator();
        }
    }

}
