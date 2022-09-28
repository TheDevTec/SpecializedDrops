package pluginprovider;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pluginprovider.commands.SpecializedCommand;
import pluginprovider.listeners.BlockBreakEvent;
import pluginprovider.listeners.EntityDeathEvent;
import pluginprovider.managers.CollectionsSystem;
import pluginprovider.modules.SpecializedModuleManager;
import pluginprovider.selectionsystems.OverrideSystem;
import pluginprovider.utils.Processor;

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
        // Load configs
        loadConfigurations();
        // Load all modules
        CollectionsSystem.reloadCollections();
        OverrideSystem.reloadOverrides();
        moduleManager = new SpecializedModuleManager();
        // Register utilities
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEvent(), this);
        // Register
        new SpecializedCommand();
    }

    @Override
    public void onDisable() {}

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private void loadConfigurations() {
        Processor.queryFolder(getInstance(), "Configuration/");
        settings = Config.loadFromFile("./plugins/SpecializedDrops/settings.yml");
        collections = Config.loadFromFile("./plugins/SpecializedDrops/Collections/collections.yml");
        overrides = Config.loadFromFile("./plugins/SpecializedDrops/Overrides/overrides.yml");
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Plugin values
    private static Plugin instance;

    // Configs
    private static Config settings;
    private static Config collections;
    private static Config overrides;

    // Module manager
    private static SpecializedModuleManager moduleManager;

    // Getters plugin values
    public static Plugin getInstance() {
        return instance;
    }

    // Getters configs values
    public static Config getCollections() {
        return collections;
    }
    public static Config getOverrides() {
        return overrides;
    }
    public static Config getSettings() {
        return settings;
    }

    // Getters manager
    public static SpecializedModuleManager getModuleManager() {
        return moduleManager;
    }


    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}