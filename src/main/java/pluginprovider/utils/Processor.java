package pluginprovider.utils;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.dataholder.DataType;
import me.devtec.shared.utility.StringUtils;
import me.devtec.theapi.bukkit.game.ItemMaker;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pluginprovider.objects.CachedAttributes;
import pluginprovider.objects.DropExecutor;
import pluginprovider.objects.Factors;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

public class Processor {

    private static final String folderPath = "/plugins/SpecializedDrops/";

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
                        Config.loadFromPlugin(p.getClass(), entry.getName(), p.getDataFolder().toPath() + "/" + entry.getName().replace(fileName, "")).save(DataType.YAML);
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
            return StringUtils.randomInt(min, max);
        } else return Integer.parseInt(text);
    }

    // Reader
    public static ItemMaker prepareMaker(String value, Player opener) {
        if (Pattern.compile("~Player").matcher(value).find()) {
            return ItemMaker.ofHead().skinName(opener.getName());
        } else if (Pattern.compile("~Head:").matcher(value).find()) {
            value = value.replace("~Head:", "");
            return ItemMaker.ofHead().skinValues(value);
        } else return ItemMaker.of(XMaterial.matchXMaterial(value).get().parseMaterial());
    }

    // Item reader
    public static ItemStack readItem(CachedAttributes rawItem, Player opener) {
        ItemStack value = prepareMaker(rawItem.getMaterial(), opener)
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
    public static ItemStack readAndExecute(CachedAttributes rawItem, Factors factors, Player opener) {
        ItemStack value = prepareMaker(rawItem.getMaterial(), opener)
                .displayName(rawItem.getName())
                .amount(parseItemAmount(rawItem.getAmount()))
                .customModel(rawItem.getCustomModelData())
                .lore(rawItem.getLore()).build();
        Map<Enchantment, Integer> enchantsReader = rawItem.getEnchantments();
        for (Enchantment var : enchantsReader.keySet()) {
            if (var != null) value.addEnchantment(var, enchantsReader.get(var));
        }
        asyncDropEvents(rawItem.getPath(), factors);
        return value;
    }

    // DropEvents executor
    public static void asyncDropEvents(String path, Factors factors) {
        new DropExecutor(Config.loadFromFile(path), factors, new CachedAttributes(path)).execute();
    }

    // Import to menu
    public static void importToMenu(Map<Character, ItemGUI> items, List<String> layout, GUI menu) {
        int counter = 0;
        for (String var : layout) {
            for (char var1 : var.toCharArray()) {
                if (var1 == ' ') { ++counter; continue; }
                if (items.containsKey(var1)) menu.setItem(counter, items.get(var1));
                ++counter;
            }
        }
    }

    // Get latest version from Spigot.org
    public static String getLatestVersion() {
        return "Not developed yet";
    }

    // Boolean translator
    public static String translateBoolean(boolean toTranslate, String falseColor, String trueColor) {
        return String.valueOf(toTranslate).replace("false", falseColor + "No").replace("true", trueColor + "Yes");
    }

}