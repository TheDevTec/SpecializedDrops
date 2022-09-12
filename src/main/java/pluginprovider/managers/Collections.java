package pluginprovider.managers;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.scheduler.Tasker;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.objects.CachedItem;

import java.io.File;
import java.util.*;

public class Collections {

    private static final String collectionsPath = "./plugins/SpecializedDrops/Collections";
    public static void reloadCollections() {
        collectionsCache.clear();
        Config collections = SpecializedDrops.getCollections();
        final Map<String, File[]> collectionWorker = new HashMap<>();
        for (String name : collections.getKeys("Collections")) {
            String path = collections.getString("Collections." + name + ".FolderName");
            File collectionFolder = new File("./plugins/SpecializedDrops/Collections/" + path);
            collectionWorker.put(name, collectionFolder.listFiles());
        }
        for (String name : collectionWorker.keySet()) {
            final Map<Double, CachedItem> collectionDrops = new HashMap<>();
            for (File item : collectionWorker.get(name)) {
                Config doubleGetter = Config.loadFromFile(item.getPath());
                String one = doubleGetter.getString("DropPercentage").split("/")[0];
                String of = doubleGetter.getString("DropPercentage").split("/")[1];
                double one_i = Integer.parseInt(one);
                double of_i = Integer.parseInt(of);
                double percentage = one_i/of_i;
                collectionDrops.put(percentage, new CachedItem(item.getPath()));
            }
            collectionsCache.put(name, new Collections(collectionDrops, collections.getStringList("Collections." + name + ".Includes")));
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private static final Map<String, Collections> collectionsCache = new HashMap<>();
    public static Collections getCollectionByName(String name) {
        return collectionsCache.get(name);
    }
    public static Collections getRandomCollection() {
        return StringUtils.getRandomFromList(new ArrayList<>(collectionsCache.values()));
    }
    public static List<Collections> getCollections() {
        return new ArrayList<>(collectionsCache.values());
    }
    public static List<String> getCollectionNames() {
        return new ArrayList<>(collectionsCache.keySet());
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private final Map<Double, CachedItem> collectionDrops;
    private final List<String> collectionIncludes;

    public Collections(Map<Double, CachedItem> collectionDrops, List<String> collectionIncludes) {
        this.collectionDrops = collectionDrops;
        this.collectionIncludes = collectionIncludes;
    }

    public ItemStack asyncPickRandomItem() {
        ItemStack[] stack = new ItemStack[]{null};
        new Tasker(){
            @Override
            public void run() {
                Map<Double, CachedItem> using = new HashMap<>(collectionDrops);
                for (String var : collectionIncludes) {
                    using.putAll(getCollectionByName(var).getCollection());
                }
                List<CachedItem> drops = new ArrayList<>();
                for (Double var : using.keySet()) {
                    double varTo = StringUtils.generateRandomDouble(0, 100);
                    if (var<=varTo) drops.add(using.get(var));
                }
                stack[0] = StringUtils.getRandomFromList(drops).asyncBuildStack();
            }
        }.runTask();
        return stack[0];
    }
    public Map<Double, CachedItem> getCollection() {
        return collectionDrops;
    }

}
