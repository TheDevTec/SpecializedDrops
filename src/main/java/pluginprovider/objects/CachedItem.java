package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.scheduler.Tasker;
import me.devtec.shared.utility.StringUtils;
import me.devtec.theapi.bukkit.BukkitLoader;
import me.devtec.theapi.bukkit.nms.NmsProvider;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.utils.Processor;

public class CachedItem {

    private final String pathToItem;
    private final double chance;

    public CachedItem(String pathToItem, double chance) {
        this.pathToItem = pathToItem;
        this.chance = chance;
    }

    // Complex actions
    public ItemStack asyncBuildStack() {
        return Processor.readItem(new CachedAttributes(pathToItem));
    }
    public CachedAttributes quickAttributes() {
        return new CachedAttributes(pathToItem);
    }
    public void asyncDropEvents(final BlockBreakEvent e) {
        new Tasker() {
            @Override
            public void run() {
                Config quickConfig = Config.loadFromFile(pathToItem);
                String x = e.getBlock().getX() + "";
                String y = e.getBlock().getY() + "";
                String z = e.getBlock().getZ() + "";
                String minedBlock = e.getBlock().getType().name();
                String player = e.getPlayer().getName();
                String legacyName = quickConfig.getString("LegacyName");
                String colorizedName = quickConfig.getString("Name");
                for (String paths : quickConfig.getKeys("DropEvents")) {
                    if (paths.equals("Title")) {
                        String line1 = quickConfig.getString("DropEvents.Title.Line1");
                        String line2 = quickConfig.getString("DropEvents.Title.Line2");
                        line1 = line1.replace("{x}", x).replace("{y}", y).replace("z", z)
                                .replace("{Player}", player).replace("{MinedBlock}", minedBlock)
                                .replace("{DropLegacyName}", legacyName).replace("{DropColorizedName}", colorizedName);
                        line2 = line2.replace("{x}", x).replace("{y}", y).replace("z", z)
                                .replace("{Player}", player).replace("{MinedBlock}", minedBlock)
                                .replace("{DropLegacyName}", legacyName).replace("{DropColorizedName}", colorizedName);
                        line1 = StringUtils.colorize(line1);
                        line2 = StringUtils.colorize(line2);
                        int in = quickConfig.getInt("DropEvents.Title.fade-in");
                        int keep = quickConfig.getInt("DropEvents.Title.keep");
                        int out = quickConfig.getInt("DropEvents.Title.fade-out");
                        Object sub = BukkitLoader.getNmsProvider().packetTitle(NmsProvider.TitleAction.SUBTITLE, line2);
                        Object main = BukkitLoader.getNmsProvider().packetTitle(NmsProvider.TitleAction.TITLE, line1, in, keep, out);
                        BukkitLoader.getPacketHandler().send(e.getPlayer(), sub);
                        BukkitLoader.getPacketHandler().send(e.getPlayer(), main);
                        continue;
                    }
                    if (paths.equals("Message")) {
                        String message = quickConfig.getString("DropEvents.Message");
                        message = message.replace("{x}", x).replace("{y}", y).replace("z", z)
                                .replace("{Player}", player).replace("{MinedBlock}", minedBlock)
                                .replace("{DropLegacyName}", legacyName).replace("{DropColorizedName}", colorizedName);
                        message = StringUtils.colorize(message);
                        Object obj = BukkitLoader.getNmsProvider().packetChat(NmsProvider.ChatType.CHAT, message);
                        BukkitLoader.getPacketHandler().send(e.getPlayer(), obj);
                        continue;
                    }
                    if (paths.equals("Broadcast")) {
                        String message = quickConfig.getString("DropEvents.Broadcast");
                        message = message.replace("{x}", x).replace("{y}", y).replace("z", z)
                                .replace("{Player}", player).replace("{MinedBlock}", minedBlock)
                                .replace("{DropLegacyName}", legacyName).replace("{DropColorizedName}", colorizedName);
                        message = StringUtils.colorize(message);
                        String finalMessage = message;
                        Bukkit.getScheduler().runTask(SpecializedDrops.getInstance(), () -> Bukkit.broadcastMessage(finalMessage));
                        continue;
                    }
                    if (paths.equals("CustomCommands")) {
                        for (String command : quickConfig.getStringList("DropEvents.CustomCommands")) {
                            Bukkit.getScheduler().runTask(SpecializedDrops.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
                        }
                    }
                }
            }
        }.runTask();
    }

    // Simple getters
    public double getChance() {
        return chance;
    }

}
