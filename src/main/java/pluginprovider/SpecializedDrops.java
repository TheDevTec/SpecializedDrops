package pluginprovider;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pluginprovider.managers.ItemGroup;
import pluginprovider.managers.Resources;
import pluginprovider.objects.Factors;

public class SpecializedDrops extends JavaPlugin implements Listener {

    @EventHandler
    public void functionTester(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            ItemStack test = ItemGroup.getRandomCollection().asyncPickRandomItem();
            e.getPlayer().getInventory().addItem(test);
        }
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        Factors factors = new Factors(e, "");
        e.getPlayer().sendMessage("");
        e.getPlayer().sendMessage(" §c| BlockFactors:");
        e.getPlayer().sendMessage("  §7| IsBlock: §e" + factors.isBlock());
        e.getPlayer().sendMessage("  §7| Time: §e" + factors.getTimeValue().name());
        e.getPlayer().sendMessage("  §7| Permissions: §e" + factors.getPermissionsValue());
        e.getPlayer().sendMessage("  §7| World: §e" + factors.getWorldValue());
        e.getPlayer().sendMessage("  §7| Block: §e" + factors.getBlockValue().name());
        e.getPlayer().sendMessage("  §7| Sneaking: §e" + factors.getSneakingValue());
        e.getPlayer().sendMessage("  §7| Biome: §e" + factors.getBiomeValue());
        e.getPlayer().sendMessage("  §7| Raining: §e" + factors.getRainingValue());
        e.getPlayer().sendMessage("  §7| Destroyer: §e" + factors.getDestroyerOrKillerValue());
        e.getPlayer().sendMessage("  §7| Fortune: §e" + factors.getFortuneLootingValue());
        e.getPlayer().sendMessage("  §7| Looking: §e" + factors.getLookingValue().name());
        e.getPlayer().sendMessage("");
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