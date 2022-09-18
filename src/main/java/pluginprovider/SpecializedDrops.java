package pluginprovider;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pluginprovider.enums.ProvidedBlockDropType;
import pluginprovider.listeners.BlockBreakEvent;
import pluginprovider.listeners.EntityDeathEvent;
import pluginprovider.managers.CollectionsSystem;
import pluginprovider.selectionsystems.OverrideSystem;
import pluginprovider.utils.AdvancedTypes;
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
        AdvancedTypes.loadTypes();
        // Register utilities
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEvent(), this);
        //
        StringBuilder enchantments = new StringBuilder();
        for (Enchantment ench : Enchantment.values()) {
            if (ench != null) enchantments.append("- ").append(ench.getName()).append("\n");
        }
        StringBuilder dropTypes = new StringBuilder();
        for (ProvidedBlockDropType blockTypes : ProvidedBlockDropType.values()) {
            dropTypes.append(blockTypes.name()).append("\n");
            for (String var : AdvancedTypes.getMaterialsByType(blockTypes)) {
                dropTypes.append("- ").append(var).append("\n");
            }
        }
        Bukkit.getLogger().info("\n" +
                "# Enchants:\n" +
                enchantments.toString() + "" +
                "\n" +
                "# DropTypes:\n" +
                dropTypes.toString() + "");
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

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}