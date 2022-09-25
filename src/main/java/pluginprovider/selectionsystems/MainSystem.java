package pluginprovider.selectionsystems;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.scheduler.Tasker;
import me.devtec.shared.utility.PercentageList;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.enums.ProvidedBlockDropType;
import pluginprovider.enums.ProvidedEntityDropType;
import pluginprovider.objects.CachedAttributes;
import pluginprovider.objects.CachedItem;
import pluginprovider.objects.EventInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainSystem {

    public static void blockDropRequest(EventInfo info) {
        new Tasker() {
            @Override
            public void run() {
                ProvidedBlockDropType block = (ProvidedBlockDropType) info.getCorrect();
                if (OverrideSystem.override(info).size() > 0) {
                    return;
                }
                Config settings = SpecializedDrops.getSettings();
                String path = "System.MainSystem.Blocks." + block.getDataPath() + ".";
                if (!settings.getBoolean(path + "Enabled")) {
                    dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
                    return;
                }
                double chance = settings.getDouble(path + "DefaultChance");
                if (StringUtils.checkProbability(chance)) {
                    dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
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
                        dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
                        return;
                    }
                    double one = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[0]);
                    double two = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[1]);
                    double ce = one/two;
                    selectedDrops.add(new CachedItem(var.getPath(), ce).asyncBuildAndExecute(info.getFactors()));
                    if (var.isAdditional()) selectedDrops.addAll(info.getDefaultDrop());
                    dropItems(info.getWorld(), info.getLocation(), selectedDrops);
                } catch (Exception e) {e.printStackTrace();}
            }
        }.runTask();
    }
    public static void entityDropRequest(EventInfo info) {
        new Tasker() {
            @Override
            public void run() {
                ProvidedEntityDropType entity = (ProvidedEntityDropType) info.getCorrect();
                if (entity == ProvidedEntityDropType.CANCELED) {
                    dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
                    return;
                }
                if (OverrideSystem.override(info).size() > 0) {
                    return;
                }
                Config settings = SpecializedDrops.getSettings();
                String path = "System.MainSystem.Blocks." + entity.getDataPath() + ".";
                if (!settings.getBoolean(path + "Enabled")) {
                    dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
                    return;
                }
                double chance = settings.getDouble(path + "DefaultChance");
                if (StringUtils.checkProbability(chance)) {
                    dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
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
                        dropItems(info.getWorld(), info.getLocation(), info.getDefaultDrop());
                        return;
                    }
                    double one = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[0]);
                    double two = Double.parseDouble(var.getConfig().getString("DropPercentage").split("/")[1]);
                    double ce = one/two;
                    selectedDrops.add(new CachedItem(var.getPath(), ce).asyncBuildAndExecute(info.getFactors()));
                    if (var.isAdditional()) selectedDrops.addAll(info.getDefaultDrop());
                    dropItems(info.getWorld(), info.getLocation(), selectedDrops);
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