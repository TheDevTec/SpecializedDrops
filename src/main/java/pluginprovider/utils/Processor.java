package pluginprovider.utils;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.dataholder.DataType;
import me.devtec.shared.utility.StringUtils;
import me.devtec.theapi.bukkit.game.ItemMaker;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pluginprovider.objects.CachedAttributes;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

public class Processor {

    private static final String folderPath = "/plugins/SpecializedDrops/";

    // Load file
    public static void queryFile(Plugin p, String fileName) {
        if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
        File[] files = p.getDataFolder().listFiles();
        boolean save = true;
        assert files != null;
        for (File var : files) {
            String name = var.getName();
            if (fileName.equalsIgnoreCase(name)) save = false;
        }
        p.saveResource(fileName, save);
    }

    // Load directory
    public static void queryFolder(Plugin p, String fileName) {
        try {
            if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
            File[] files = p.getDataFolder().listFiles();
            boolean save = true;
            assert files != null;
            for (File var : files) {
                String name = fileName.replace("/", "");
                if (name.equalsIgnoreCase(var.getName())) save = false;
            }
            if (save) {
                File pluginFile = new File(p.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                JarFile jar = new JarFile(pluginFile);
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().startsWith(fileName) && !entry.getName().endsWith("/")) {
                        Config.loadFromPlugin(p.getClass(), entry.getName(), p.getDataFolder().toPath() + "/" + entry.getName()).save(DataType.YAML);
                    }
                }
                jar.close();
            }
        } catch (IOException | URISyntaxException exception) {}
    }

    // ~Random(min,max)
    public static int parseItemAmount(String text) {
        if (Pattern.compile("~Random\\((.?),(.?)\\)").matcher(text).find()) {
            text = text.replace("~Random(", "").replace(")", "");
            int min = Integer.parseInt(text.split(",")[0]);
            int max = Integer.parseInt(text.split(",")[1]);
            return StringUtils.generateRandomInt(min, max);
        } else return Integer.parseInt(text);
    }

    // Item reader
    public static ItemStack readItem(CachedAttributes rawItem) {
        ItemStack value = ItemMaker.of(rawItem.getMaterial())
                .displayName(rawItem.getName())
                .amount(parseItemAmount(rawItem.getAmount()))
                .customModel(rawItem.getCustomModelData())
                .lore(rawItem.getLore()).build();
        Map<Enchantment, Integer> enchantsReader = rawItem.getEnchantments();
        for (Enchantment var : enchantsReader.keySet()) {
            if (var != null) value.addEnchantment(var, enchantsReader.get(var));
        }
        return value;
    }

    // DropEvents executor
    public static void asyncDropEvents(String path) {}

}