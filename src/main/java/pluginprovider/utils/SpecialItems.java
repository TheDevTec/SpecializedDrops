package pluginprovider.utils;

import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.enums.updater.UpdateStatus;
import pluginprovider.modules.itemmaker.materials.Pagination;

public class SpecialItems {

    // Items
    public static ItemStack getPluginHead() {
        ItemMaker maker = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZlZmIzZWE4ZWJkZjIzZmI3MjZiNzg2YTQxYTYyNTFmMGRmY2VlYjE5YWFhNzI3MDBhNDc3YWZlZjNjYjUyNSJ9fX0=")
                .amount(1).displayName("§c➥ SpecializedDrops").lore(
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
                        " &7➥ Actions",
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
                .displayName("&e➥ Requester")
                .lore(
                        "",
                        " &7➥ Name: &e" + p.getName(),
                        " &7➥ UUID: &e" + p.getUniqueId(),
                        " &7➥ SD-Admin: &e" + Processor.translateBoolean(p.hasPermission("sd.admin"), "&e", "&e"),
                        " &7➥ Ping: &e" + p.getPing(),
                        ""
                );
        return maker.build();
    }
    public static ItemStack getCreators() {
        ItemMaker maker = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTg0ZTBhYzczZDc0YTI5YmQ2YTMyZTg5MTJhM2U4ODMyNjQ4OTljMTcwZjg3OWNjYTE4NTA2YjY1NGFiMmNkNCJ9fX0=")
                .displayName("&3➥ DevTec team")
                .lore(
                        "",
                        " &7➥ Hello, We're DevTec!",
                        "    &7We're creating free plugins",
                        "    &7for Spigot with our powerful",
                        "    &7API called 'TheAPI'!",
                        "",
                        " &7➥ Members:",
                        "  &3➥ &3StraikerinaCZ",
                        "  &3➥ &3Houska02",
                        "  &3➥ &3M3II0",
                        "",
                        " &7➥ Actions",
                        "  &e➥ Any mouse-click",
                        "   &7➥ (Contacts)",
                        ""
                );
        return maker.build();
    }
    public static ItemStack getArrow(ArrowType type, Pagination<XMaterial> using, int currentPage) {
        if (type == ArrowType.LEFT) {
            // Lower
            if (currentPage-1 > -1) {
                return ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VlMDkyOTA2MjBjOGIyOTU5MWZkMDAxZTczOTVjZjMyNDY0ZTJmZDRiNTQ1OWZmNzU3ZDBjZDhlMWM3NTJmOCJ9fX0=")
                        .displayName("&aPrevious page").build();
            } else {
                return ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM5ZWY0NTg1MTJlNmM1YzgwMWRlYzE2MmE1MGUwMDg0ZGIyYzBmNDRiNThjZDIzNzY0ODY5MzM0YTg0MDAzZCJ9fX0=")
                        .displayName("&7Previous page").build();
            }
        } else {
            // Higher
            if (using.totalPages()-1 > currentPage+1) {
                return ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWU1ZDlkYzk1MTE5NjIyM2FjNzI5NDc4MDNhMTA5Mzk2NWVhODk4NmE5NWJkOWNkNDJlM2QwMDI0MDExM2YxMCJ9fX0=")
                        .displayName("&aNext page").build();
            } else {
                return ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFkMWZlNzk0MjgzMDZiNGRiN2NjN2JjYTM2ODE4ZjM1Y2I4NTZhZWU3OGFmZDBmOTAxNjBiOWRjMDc5MmMzIn19fQ==")
                        .displayName("&7Next page").build();
            }
        }
    }

}