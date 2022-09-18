package pluginprovider.selectionsystems;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.scheduler.Tasker;
import me.devtec.shared.utility.PercentageList;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.enums.ProvidedBlockDropType;
import pluginprovider.objects.CachedAttributes;
import pluginprovider.objects.CachedItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainSystem {

    public static void dropRequest(final World world, final Location loc, final ProvidedBlockDropType blockType, final BlockBreakEvent e, final List<ItemStack> defaultDrops) {
        new Tasker() {
            @Override
            public void run() {
                if (OverrideSystem.override(e, defaultDrops)) {
                    return;
                }
                Config settings = SpecializedDrops.getSettings();
                String path = "System.MainSystem.Blocks." + blockType.getDataPath() + ".";
                if (!settings.getBoolean(path + "Enabled")) {
                    dropItems(world, loc, defaultDrops);
                    return;
                }
                double chance = settings.getDouble(path + "DefaultChance");
                if (StringUtils.checkProbability(chance)) {
                    dropItems(world, loc, defaultDrops);
                    return;
                }
                List<String> folders = new ArrayList<>();
                folders.add(settings.getString(path + "Folder"));
                folders.addAll(settings.getStringList(path + "Includes"));
                String pathToFolder = "./plugins/SpecializedDrops/Items/";
                PercentageList<CachedAttributes> items = new PercentageList<>();
                for (String var : folders) {
                    var = pathToFolder + var + "/";
                    File[] itemsL = new File(var).listFiles();
                    if (itemsL == null) continue;
                    for (File itemFile : itemsL) {
                        CachedAttributes ca = new CachedAttributes(itemFile.toPath().toString());
                        double one = Double.parseDouble(ca.getConfig().getString("DropPercentage").split("/")[0]);
                        double two = Double.parseDouble(ca.getConfig().getString("DropPercentage").split("/")[1]);
                        double ce = one/two;
                        items.add(ca, ce);
                    }
                }
                try {
                    List<ItemStack> selectedDrops = new ArrayList<>();
                    CachedAttributes var = items.getRandom();
                    if (var == null) {
                        dropItems(world, loc, defaultDrops);
                        return;
                    }
                    double one = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[0]);
                    double two = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[1]);
                    double ce = one/two;
                    selectedDrops.add(new CachedItem(var.getPath(), ce).asyncBuildAndExecute());
                    if (var.isAdditional()) selectedDrops.addAll(defaultDrops);
                    dropItems(world, loc, selectedDrops);
                } catch (Exception e) {e.printStackTrace();}
            }
        }.runTask();
    }
    private static void dropItems(World world, Location loc, List<ItemStack> drops) {
        Bukkit.getScheduler().runTask(SpecializedDrops.getInstance(), () -> {
            for (ItemStack var : drops) {
                if (world != null) world.dropItem(loc, var);
            }
        });
    }

}