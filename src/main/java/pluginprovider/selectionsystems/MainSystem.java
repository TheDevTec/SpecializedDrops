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
import pluginprovider.objects.CachedAttributes;
import pluginprovider.objects.CachedItem;
import pluginprovider.objects.EventInfo;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainSystem {

    public static void dropRequest(EventInfo info) {
        new Tasker() {
            @Override
            public void run() {
                long nanos = -System.nanoTime();
                if (OverrideSystem.override(info).size() > 0) {
                    return;
                }
                Config settings = SpecializedDrops.getSettings();
                String path = "System.MainSystem.Blocks." + info.getCorrect().getDataPath() + ".";
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
                        Config config = ca.getConfig();
                        String[] percentage = config.getString("DropPercentage").split("/");
                        double one = Double.parseDouble(percentage[0]);
                        double two = Double.parseDouble(percentage[1]);
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
                    String[] percentage = var.getConfig().getString("DropPercentage").split("/");
                    double one = Double.parseDouble(percentage[0]);
                    double two = Double.parseDouble(percentage[1]);
                    double ce = one/two;
                    selectedDrops.add(new CachedItem(var.getPath(), ce).asyncBuildAndExecute(info.getFactors(), info.getDestroyer()));
                    if (var.isAdditional()) selectedDrops.addAll(info.getDefaultDrop());
                    dropItems(info.getWorld(), info.getLocation(), selectedDrops);
                    nanos += System.nanoTime();
                    System.out.println(new DecimalFormat("#.###").format(nanos/1e+6));
                } catch (Exception e) {e.printStackTrace();}
            }
        }.runTask();
    }
    private static void dropItems(World world, Location loc, Collection<ItemStack> drops) {
        loc.add(0.5, 0, 0.5);
        Bukkit.getScheduler().runTaskLater(SpecializedDrops.getInstance(), () -> {
            for (ItemStack var : drops) {
                if (world != null) world.dropItem(loc, var);
            }
        }, 1);
    }

}