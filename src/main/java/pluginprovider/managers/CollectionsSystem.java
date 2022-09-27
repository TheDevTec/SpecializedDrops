package pluginprovider.managers;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.utility.PercentageList;
import me.devtec.shared.utility.StringUtils;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.objects.CachedAttributes;
import pluginprovider.objects.CachedItem;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Factors;
import pluginprovider.utils.Processor;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionsSystem {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Builder
    public static void reloadCollections() {
        collectionsCache.clear();
        Config collections = SpecializedDrops.getCollections();
        final Map<String, File[]> collectionWorker = new HashMap<>();
        for (String name : collections.getKeys("Configuration/Collections")) {
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
            collectionsCache.put(name, new CollectionsSystem(collectionDrops, collections.getStringList("Collections." + name + ".Includes"), collections.getInt("Collections." + name + ".MaxDrops"), collections.getBoolean("Collections." + name + ".DropWithDefaults")));
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Public API
    private static final Map<String, CollectionsSystem> collectionsCache = new HashMap<>();
    public static CollectionsSystem getCollectionByName(String name) {
        return collectionsCache.get(name);
    }
    public static CollectionsSystem getRandomCollection() {
        return StringUtils.randomFromCollection(getCollections().parallelStream().collect(Collectors.toList()));
    }
    public static Collection<CollectionsSystem> getCollections() {
        return collectionsCache.values();
    }
    public static Set<String> getCollectionNames() {
        return collectionsCache.keySet();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Values
    private final List<CachedItem> collectionDrops;
    private final List<String> collectionIncludes;
    private final int maxDrops;
    private final boolean withDefaults;

    // Constructor
    public CollectionsSystem(List<CachedItem> collectionDrops, List<String> collectionIncludes, int maxDrops, boolean withDefaults) {
        this.collectionDrops = collectionDrops;
        this.collectionIncludes = collectionIncludes;
        this.maxDrops = maxDrops;
        this.withDefaults = withDefaults;
    }

    // Functions
    public List<ItemStack> asyncPickRandomItems(EventInfo info) {
        List<ItemStack> drops = new ArrayList<>();
        PercentageList<CachedItem> randomize = getPercentageCollection();
        for (int c = 0; c <= maxDrops; ++c) {
            CachedItem selected = randomize.getRandom();
            runAsyncDropEvents(selected.quickAttributes(), info.getFactors());
            drops.add(selected.asyncBuildStack(info.getDestroyer()));
        }
        if (withDefaults) drops.addAll(info.getDefaultDrop());
        return drops;
    }
    public void runAsyncDropEvents(CachedAttributes rawItem, Factors factors) {
        Processor.asyncDropEvents(rawItem.getPath(), factors);
    }

    // Getter
    public List<CachedItem> getCollection() {
        List<CachedItem> defaultCollection = new ArrayList<>(collectionDrops);
        for (String var : collectionIncludes) {
            defaultCollection.addAll(getCollectionByName(var).getCollection());
        }
        return defaultCollection;
    }
    private PercentageList<CachedItem> getPercentageCollection() {
        PercentageList<CachedItem> value = new PercentageList<>();
        List<CachedItem> drops = new ArrayList<>(getCollection());
        for (CachedItem item : drops) {
            value.add(item, item.getChance());
        }
        return value;
    }
    public int getMaxDrops() {
        return maxDrops;
    }
    public boolean isWithDefaults() {
        return withDefaults;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}
