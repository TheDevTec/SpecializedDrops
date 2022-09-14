package pluginprovider.managers;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.utility.PercentageList;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.objects.CachedItem;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ItemGroup {

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
            final List<CachedItem> collectionDrops = new ArrayList<>();
            for (File item : collectionWorker.get(name)) {
                Config doubleGetter = Config.loadFromFile(item.getPath());
                String one = doubleGetter.getString("DropPercentage").split("/")[0];
                String of = doubleGetter.getString("DropPercentage").split("/")[1];
                double one_i = Integer.parseInt(one);
                double of_i = Integer.parseInt(of);
                double percentage = one_i/of_i;
                collectionDrops.add(new CachedItem(item.getPath(), percentage));
            }
            collectionsCache.put(name, new ItemGroup(collectionDrops, collections.getStringList("Collections." + name + ".Includes")));
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private static final Map<String, ItemGroup> collectionsCache = new HashMap<>();
    public static ItemGroup getCollectionByName(String name) {
        return collectionsCache.get(name);
    }
    public static ItemGroup getRandomCollection() {
        return StringUtils.getRandomFromList(getCollections().parallelStream().collect(Collectors.toList()));
    }
    public static Collection<ItemGroup> getCollections() {
        return collectionsCache.values();
    }
    public static Set<String> getCollectionNames() {
        return collectionsCache.keySet();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    private final List<CachedItem> collectionDrops;
    private final List<String> collectionIncludes;

    public ItemGroup(List<CachedItem> collectionDrops, List<String> collectionIncludes) {
        this.collectionDrops = collectionDrops;
        this.collectionIncludes = collectionIncludes;
    }

    public ItemStack asyncPickRandomItem() {
        PercentageList<CachedItem> item = new PercentageList<>();
        for (String using : collectionIncludes) {
            for (CachedItem var : ItemGroup.getCollectionByName(using).getCollection()) {
                if (var==null) {
                    System.out.println("Null!");
                    continue;
                }
                item.add(var, var.getChance());
            }
        }
        for (CachedItem loop : collectionDrops) {
            if (loop==null) {
                System.out.println("Null!");
                continue;
            }
            item.add(loop, loop.getChance());
        }
        CachedItem randomSelection = item.get();
        return randomSelection.asyncBuildStack();
    }
    public List<CachedItem> getCollection() {
        return collectionDrops;
    }

}
