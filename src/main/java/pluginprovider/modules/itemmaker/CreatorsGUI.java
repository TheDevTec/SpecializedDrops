package pluginprovider.modules.itemmaker;

import me.devtec.theapi.bukkit.BukkitLoader;
import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.gui.EmptyItemGUI;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.utils.Processor;

import java.util.*;

public class CreatorsGUI {

    // Values
    private final UUID player;
    private boolean active;
    private final List<QuickItem> history;

    // Constructor
    private CreatorsGUI(UUID player) {
        this.player = player;
        this.active = false;
        this.history = new ArrayList<>();
    }

    // Builder
    public static CreatorsGUI parseGUI(Player p) {
        return new CreatorsGUI(p.getUniqueId());
    }

    // Functions
    public void setInactive() {
        this.active = false;
    }
    public void setActive() {
        this.active = true;
    }

    // Getter
    public Player getAsyncPlayer() {
        Player returner = null;
        for (Player var : BukkitLoader.getOnlinePlayers()) {
            if (var.getUniqueId().equals(player)) {
                returner = var;
                break;
            }
        }
        return returner;
    }

    // Creator
    public void openCreator() {
        try {
            setActive();
            GUI menu = new GUI("&8➥ ItemCreator", 54);
            Map<Character, ItemGUI> items = new HashMap<>();
            List<String> layout = Arrays.asList(
                    "123456789",
                    "---------",
                    "         ",
                    "         ",
                    "  -----  ",
                    "---qp+---"
            );

            // -
            ItemStack _border = ItemMaker.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                    .displayName("&7").build();
            items.put('-', new EmptyItemGUI(_border));

            // numbers
            ItemStack _null = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzc3ZDRhMjA2ZDc3NTdmNDc5ZjMzMmVjMWEyYmJiZWU1N2NlZjk3NTY4ZGQ4OGRmODFmNDg2NGFlZTdkM2Q5OCJ9fX0=")
                    .displayName("&d➥ Empty slot")
                    .lore(
                            "",
                            " &7➥ This is history of Your created items...",
                            " &d➥ This slot is currently empty!",
                            ""
                    ).build();
            for (int c = 1; c < 10; ++c) {
                if (history.size() < c) items.put((char) c, new EmptyItemGUI(_null));
                else {
                    QuickItem item = history.get(c - 1);
                    items.put((char) c, new EmptyItemGUI(item.getFormatted(getAsyncPlayer())));
                }
            }

            // Finisher
            Processor.importToMenu(items, layout, menu);
            menu.open(getAsyncPlayer());
        } catch (Exception e) {e.printStackTrace();}
    }

}