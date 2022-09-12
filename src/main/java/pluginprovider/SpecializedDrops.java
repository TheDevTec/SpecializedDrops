package pluginprovider;

import me.devtec.shared.dataholder.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pluginprovider.managers.Collections;

public class SpecializedDrops extends JavaPlugin implements Listener {

    @EventHandler
    public void functionTester(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {

        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        loadConfigurations();
    }

    @Override
    public void onDisable() {}

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private void loadConfigurations() {
        collections = Config.loadFromPlugin(getInstance().getClass(), "Collections/collections.yml", "/plugins/SpecializedDrops/Collections/collections.yml");
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Plugin values
    private static Plugin instance;

    // Configs
    private static Config collections;

    // Getters plugin values
    public static Plugin getInstance() {
        return instance;
    }

    // Getters configs values
    public static Config getCollections() {
        return collections;
    }

}