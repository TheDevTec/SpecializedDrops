package pluginprovider;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pluginprovider.managers.ItemGroup;
import pluginprovider.managers.Resources;

public class SpecializedDrops extends JavaPlugin implements Listener {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Resources.queryFile(getInstance(), "settings.yml");
        Resources.queryFolder(getInstance(), "Collections/");
        Resources.queryFolder(getInstance(), "Overrides/");
        loadConfigurations();
        ItemGroup.reloadCollections();
        Bukkit.getPluginManager().registerEvents(this, this);
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