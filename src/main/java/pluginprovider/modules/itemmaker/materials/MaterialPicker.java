package pluginprovider.modules.itemmaker.materials;

import me.devtec.shared.scheduler.Tasker;
import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.gui.*;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.modules.itemmaker.CreatorsGUI;
import pluginprovider.utils.ArrowType;
import pluginprovider.utils.SpecialItems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialPicker {

    // Values
    private static Pagination<XMaterial> MATERIALS;
    private static final char materialCharacter = 'x';

    // Builder
    public static void openFor(Player p, CreatorsGUI GUI, String searchCriteria, int... page) {

        // Values
        me.devtec.theapi.bukkit.gui.GUI gui = new GUI("&8➥ Material Picker", 54);
        Map<Character, ItemGUI> items = new HashMap<>();
        List<String> layout = Arrays.asList(
                "---f-r---",
                "xxxxxxxxx",
                "xxxxxxxxx",
                "xxxxxxxxx",
                "xxxxxxxxx",
                "<---e--->"
        );

        // Builder
        int materialValue = 0;
        for (String var : layout) {
            for (char var1 : var.toCharArray()) {
                if (var1 == materialCharacter) ++materialValue;
            }
        }
        if (MATERIALS == null) {
            MATERIALS = new Pagination<>(materialValue);
            for (XMaterial material : XMaterial.VALUES) {
                if (material.isSupported() && material.parseMaterial().isItem() && material != XMaterial.AIR) {
                    MATERIALS.add(material);
                }
            }
        }

        int pageinfo = (page.length > 0) ? page[0] : 0;

        // r
        ItemStack _reset = ItemMaker.of(XMaterial.NAME_TAG.parseMaterial())
                .displayName("&c➥ Reset filter")
                .lore(
                        "",
                        " &7➥ Current filter: " + ((searchCriteria.isEmpty())? "None" : searchCriteria),
                        " &a➥ Click to reset name filter!",
                        ""
                ).build();
        items.put('r', new ItemGUI(_reset) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                if (!searchCriteria.isEmpty()) openFor(player, GUI, "", pageinfo);
            }
        });

        // f
        ItemStack _find = ItemMaker.of(XMaterial.ANVIL.parseMaterial())
                .displayName("&a➥ Search by name")
                .lore(
                        "&7➥ Click to set filter!"
                ).build();
        items.put('f', new ItemGUI(_find) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                AnvilGUI anvil = new AnvilGUI("&8➥ Criteria") {
                    public boolean onIteractItem(Player player, ItemStack item, me.devtec.theapi.bukkit.gui.GUI.ClickType type, int slot, boolean gui) {
                        if (gui && slot == 2) {
                            openFor(p, GUI, getRenameText(), 0);
                        }
                        return true;
                    }
                };
                ItemStack completer = ItemMaker.of(XMaterial.PAPER.parseMaterial())
                        .lore("&d➥ Click to confirm filter!").build();
                anvil.open(p);
                new Tasker() {
                    @Override
                    public void run() {
                        anvil.setItem(0, new EmptyItemGUI(completer));
                    }
                }.runLater(5);
            }
        });

        // -
        ItemStack _border = ItemMaker.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).displayName("&7").build();
        items.put('-', new EmptyItemGUI(_border));

        // Selector
        Pagination<XMaterial> using = new Pagination<>(materialValue);
        using.addAll(getByCriteria(searchCriteria, materialValue));
        int totalpages = using.totalPages();

        // <
        ItemStack left_arrow = SpecialItems.getArrow(ArrowType.LEFT, using, (page.length > 0) ? page[0] : 0);
        ItemStack right_arrow = SpecialItems.getArrow(ArrowType.RIGHT, using, (page.length > 0) ? page[0] : 0);
        items.put('<', new ItemGUI(left_arrow) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                if ((pageinfo - 1) > -1) {
                    openFor(p, GUI, searchCriteria, page[0] - 1);
                }
            }
        });
        items.put('>', new ItemGUI(right_arrow) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                if (totalpages - 1 > pageinfo + 1) { //
                    openFor(p, GUI, searchCriteria, page[0] + 1);
                }
            }
        });

        // e
        ItemStack e = SpecialItems.getCloseHead();
        items.put('e', new ItemGUI(e) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                GUI.update();
            }
        });

        // Setter
        int listCounter = -1;
        int counter = 0;
        // Adder
        for (String var : layout) {
            for (char var1 : var.toCharArray()) {
                if (var1 == materialCharacter) {
                    try {
                        ++listCounter;
                        XMaterial material = using.getPage(pageinfo).get(listCounter);
                        if (listCounter > using.getPage(pageinfo).size() - 1) {
                            ItemStack builtMaterial = ItemMaker.of(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial())
                                    .displayName("&7").build();
                            gui.setItem(counter, new EmptyItemGUI(builtMaterial));
                        } else if (material.isSupported() && material.parseMaterial().isItem() && material != XMaterial.AIR) {
                            ItemStack builtMaterial = ItemMaker.of(material.parseMaterial())
                                    .displayName(formatMaterial(material.parseMaterial()))
                                    .lore("&7➥ Click to select this material!").build();
                            gui.setItem(counter, new ItemGUI(builtMaterial) {
                                @Override
                                public void onClick(Player player, HolderGUI holderGUI, me.devtec.theapi.bukkit.gui.GUI.ClickType clickType) {
                                    GUI.getCurrent().setMaterial(builtMaterial.getType());
                                    GUI.update();
                                }
                            });
                            ++counter;
                        }
                    } catch (IndexOutOfBoundsException ignore) {
                        ++counter;
                    }
                } else {
                    if (items.containsKey(var1)) gui.setItem(counter, items.get(var1));
                    ++counter;
                }
            }
        }

        // Finalizer
        gui.open(p);
    }

    // Depends
    private static List<XMaterial> getByCriteria(String searchCriteria, int listSize) {
        List<XMaterial> returner = new Pagination<>(listSize);
        if (searchCriteria.isEmpty()) return MATERIALS;
        for (XMaterial material : MATERIALS) {
            if (material.name().toUpperCase().contains(searchCriteria.toUpperCase().replace(" ", "_"))) {
                returner.add(material);
            }
        }
        return returner;
    }
    private static String formatMaterial(Material material) {
        StringBuilder value = new StringBuilder("&b");
        String[] words = material.name().split("_");
        int looped = 0;
        for (String toFormat : words) {
            if (toFormat.equals("TO")) value.append("to ");
            else if (toFormat.equals("OF")) value.append("of ");
            else {
                char[] chars = toFormat.toCharArray();
                value.append(String.valueOf(chars[0]).toUpperCase());
                boolean skip = true;
                for (Character character : chars) {
                    if (skip) {
                        skip = false;
                        continue;
                    }
                    value.append(String.valueOf(character).toLowerCase());
                }
                if (words.length > looped) value.append(" ");
            }
            ++looped;
        }
        return value.toString();
    }

}