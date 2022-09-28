package pluginprovider.modules.itemmaker;

import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.gui.EmptyItemGUI;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.HolderGUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.commands.SpecializedCommand;
import pluginprovider.modules.itemmaker.materials.MaterialPicker;
import pluginprovider.utils.Processor;
import pluginprovider.utils.SpecialItems;

import java.util.*;

public class CreatorsGUI {

    // Values
    private final UUID player;
    private boolean active;
    private final List<QuickItem> history;
    private QuickItem current;

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
    public QuickItem getCurrent() {
        if (current == null) current = QuickItem.generate();
        return current;
    }

    // Getter
    public Player getPlayer() {
        return Bukkit.getPlayer(player);
    }
    public boolean isActive() {
        return active;
    }

    // Creator
    public void update() {
        try {
            setActive();
            GUI menu = new GUI("&8➥ ItemCreator", 54);
            Map<Character, ItemGUI> items = new HashMap<>();
            List<String> layout = Arrays.asList(
                    "123456789",
                    "---------",
                    "m        ",
                    "         ",
                    "  -----  ",
                    "---qp+---"
            );

            // -
            ItemStack _border = ItemMaker.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                    .displayName("&7").build();
            items.put('-', new EmptyItemGUI(_border));

            // q
            ItemStack q = SpecialItems.getCloseHead();
            items.put('q', new ItemGUI(q) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    SpecializedCommand.getMainGUI(player).open(player);
                }
            });

            // +
            ItemStack _plus = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZmMzE0MzFkNjQ1ODdmZjZlZjk4YzA2NzU4MTA2ODFmOGMxM2JmOTZmNTFkOWNiMDdlZDc4NTJiMmZmZDEifX19")
                    .displayName("&a➥ Create")
                    .lore(
                            "",
                            " &7| Click to get Item!",
                            ""
                    ).build();
            items.put('+', new ItemGUI(_plus) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    if (current != null) {
                        holderGUI.close(player);
                        player.getInventory().addItem(current.getFormatted(getPlayer()));
                        history.add(QuickItem.generate(current));
                        current = null;
                    }
                }
            });

            // p
            ItemStack p;
            if (current == null) p = ItemMaker.of(XMaterial.PAPER.parseMaterial())
                    .displayName("&d➥ Start with editing Your item!")
                    .lore(
                            "",
                            " &7➥ This is preview of your Item!",
                            ""
                    ).build();
            else if (current.getMaterial() == null) p = ItemMaker.of(XMaterial.PAPER.parseMaterial())
                    .displayName("&d➥ Start with editing Your item!")
                    .lore(
                            "",
                            " &7➥ This is preview of your Item!",
                            ""
                    ).build();
            else p = current.getFormatted(getPlayer());
            items.put('p', new EmptyItemGUI(p));

            // numbers
            ItemStack _null = ItemMaker.ofHead().skinValues("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzc3ZDRhMjA2ZDc3NTdmNDc5ZjMzMmVjMWEyYmJiZWU1N2NlZjk3NTY4ZGQ4OGRmODFmNDg2NGFlZTdkM2Q5OCJ9fX0=")
                    .displayName("&d➥ Empty slot")
                    .lore(
                            "",
                            " &7➥ This is history of Your created items...",
                            " &d➥ This slot is currently empty!",
                            ""
                    ).build();

            // Numbers
            int size = history.size();
            if (history.size() >= 1) items.put('1', new ItemGUI(history.get(size-1).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(0));
                    update();
                }
            });
            else items.put('1', new EmptyItemGUI(_null));
            if (history.size() >= 2) items.put('2', new ItemGUI(history.get(size-2).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(1));
                    update();
                }
            });
            else items.put('2', new EmptyItemGUI(_null));
            if (history.size() >= 3) items.put('3', new ItemGUI(history.get(size-3).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(2));
                    update();
                }
            });
            else items.put('3', new EmptyItemGUI(_null));
            if (history.size() >= 4) items.put('4', new ItemGUI(history.get(size-4).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(3));
                    update();
                }
            });
            else items.put('4', new EmptyItemGUI(_null));
            if (history.size() >= 5) items.put('5', new ItemGUI(history.get(size-5).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(4));
                    update();
                }
            });
            else items.put('5', new EmptyItemGUI(_null));
            if (history.size() >= 6) items.put('6', new ItemGUI(history.get(size-6).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(5));
                    update();
                }
            });
            else items.put('6', new EmptyItemGUI(_null));
            if (history.size() >= 7) items.put('7', new ItemGUI(history.get(size-7).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(6));
                    update();
                }
            });
            else items.put('7', new EmptyItemGUI(_null));
            if (history.size() >= 8) items.put('8', new ItemGUI(history.get(size-8).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(7));
                    update();
                }
            });
            else items.put('8', new EmptyItemGUI(_null));
            if (history.size() >= 9) items.put('9', new ItemGUI(history.get(size-9).getFormatted(getPlayer())) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    current = QuickItem.generate(history.get(8));
                    update();
                }
            });
            else items.put('9', new EmptyItemGUI(_null));

            // Options
            // m
            ItemStack material = ItemMaker.of(Material.GRASS_BLOCK).displayName("&fClick to change material!").build();
            items.put('m', new ItemGUI(material) {
                @Override
                public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                    MaterialPicker.openFor(player, CreatorsGUI.this, "", 0);
                }
            });

            // Finisher
            Processor.importToMenu(items, layout, menu);
            menu.open(getPlayer());
        } catch (Exception e) {e.printStackTrace();}
    }

}