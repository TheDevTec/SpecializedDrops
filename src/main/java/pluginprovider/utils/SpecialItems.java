package pluginprovider.utils;

import me.devtec.theapi.bukkit.game.ItemMaker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.enums.updater.UpdateStatus;

public class SpecialItems {

    // Items
    public static ItemStack getPluginHead() {
        ItemMaker maker = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZlZmIzZWE4ZWJkZjIzZmI3MjZiNzg2YTQxYTYyNTFmMGRmY2VlYjE5YWFhNzI3MDBhNDc3YWZlZjNjYjUyNSJ9fX0=")
                .amount(1).displayName("§cSpecializedDrops").lore(
                        "",
                        " &7➥ Welcome to &cSpecializedDrops &7source!",
                        " &7➥ Created by &cDevTec &7©",
                        "",
                        " &7➥ Version: &c" + SpecializedDrops.getInstance().getDescription().getVersion(),
                        " &7➥ Latest: &c" + Processor.getLatestVersion(),
                        "",
                        " &7➥ Plugin version support: &c1.8 - 1.19.2",
                        " &7➥ Plugin depends: &cTheAPI",
                        " &7➥ Base command: &c/sd",
                        "",
                        "  &e➥ Right click to refresh",
                        "   &7➥ " + UpdateStatus.parseUpdateStatus(SpecializedDrops.getInstance().getDescription().getVersion(), Processor.getLatestVersion()).getFormatted(),
                        ""
                );
        return maker.build();
    }
    public static ItemStack getCloseHead() {
        ItemMaker maker = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjc1NDgzNjJhMjRjMGZhODQ1M2U0ZDkzZTY4YzU5NjlkZGJkZTU3YmY2NjY2YzAzMTljMWVkMWU4NGQ4OTA2NSJ9fX0=")
                .displayName("&cClose").lore(
                        "",
                        " &7➥ Click to &cClose&7!",
                        ""
                );
        return maker.build();
    }
    public static ItemStack getRequester(Player p) {
        ItemMaker maker = ItemMaker.ofHead().skinName(p.getName())
                .displayName("&7➥ Requester: &c" + p.getName())
                .lore(
                        "",
                        " &7➥ Name: &c" + p.getName(),
                        " &7➥ UUID: &c" + p.getUniqueId(),
                        " &7➥ SD-Admin: &c" + Processor.translateBoolean(p.hasPermission("sd.admin"), "&c", "&c"),
                        " &7➥ Ping: &c" + p.getPing(),
                        ""
                );
        return maker.build();
    }

}
