package pluginprovider.commands;

import me.devtec.shared.commands.holder.CommandExecutor;
import me.devtec.shared.commands.structures.CommandStructure;
import me.devtec.shared.scheduler.Tasker;
import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.gui.EmptyItemGUI;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.HolderGUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.coreapi.CoreMessage;
import pluginprovider.utils.Processor;
import pluginprovider.utils.SpecialItems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecializedCommand {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Constructor & Register
    public SpecializedCommand() {
        new Tasker() {
            @Override
            public void run() {

                // Values
                CommandExecutor<CommandSender> main_command = (commandSender, commandStructure, strings) -> {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        GUI build = getMainGUI(p);
                        build.open(p);
                    }
                };

                // Command register
                CommandStructure.create(CommandSender.class, (s, permission, corrector) -> s.hasPermission(permission), main_command)
                        .permission("sd.admin")
                        .build().register("sd");
            }
        }.runTask();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Depends
    public static GUI getMainGUI(Player p) {
        GUI menu = new GUI("§8➥ SpecializedDrops", 54);
        Map<Character, ItemGUI> items = new HashMap<>();
        List<String> layout = Arrays.asList(
                "--r-h-c--",
                "---------",
                "---------",
                "---------",
                "---------",
                "----x----"
        );

        // -
        ItemStack _border = ItemMaker.of(XMaterial.matchXMaterial("BLACK_STAINED_GLASS_PANE").get().parseMaterial()).displayName("&7").build();
        items.put('-', new EmptyItemGUI(_border));

        // h
        ItemStack h = SpecialItems.getPluginHead();
        items.put('h', new EmptyItemGUI(h));

        // x
        ItemStack x = SpecialItems.getCloseHead();
        items.put('x', new ItemGUI(x) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                holderGUI.close(player);
            }
        });

        // r
        ItemStack r = SpecialItems.getRequester(p);
        items.put('r', new EmptyItemGUI(r));

        // c
        ItemStack c = SpecialItems.getCreators();
        items.put('c', new ItemGUI(c) {
            @Override
            public void onClick(Player player, HolderGUI holderGUI, GUI.ClickType clickType) {
                CoreMessage message = new CoreMessage();
                message.addText("\n");
                message.addText("&7| &3The DevTec\n");
                message.addText("  &7| &fhttps://github.com/TheDevTec\n", "&bClick to open!", ClickEvent.Action.OPEN_URL, "https://github.com/TheDevTec");
                message.addText("\n");
                message.addText(" &8| &7If You want to support us, follow us or donate!\n");
                message.sendTo(player);
                holderGUI.close(player);
            }
        });

        Processor.importToMenu(items, layout, menu);
        return menu;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}