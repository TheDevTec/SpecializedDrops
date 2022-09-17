package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedAttributes {

    // Item Options
    private final String material;
    private final String name;
    private final String legacy_name;
    private final String amount;
    private final int customModelData;
    private final List<String> lore;
    private final Map<String, Integer> enchantments;

    // Others
    private final String path;
    private final String dropPercentage;
    private final boolean isAdditional;

    // Constructor
    public CachedAttributes(String path) {
        Config var = Config.loadFromFile(path);
        this.path = path;
        if (var.getKeys().contains("Material")) material = var.getString("Material"); else material = "STONE";
        if (var.getKeys().contains("Name")) name = var.getString("Name"); else name = "Undefined name";
        if (var.getKeys().contains("LegacyName")) legacy_name = var.getString("LegacyName"); else legacy_name = "Undefined legacy name";
        if (var.getKeys().contains("Amount")) amount = var.getString("Amount"); else amount = "1";
        if (var.getKeys().contains("CustomModelData")) customModelData = var.getInt("CustomModelData"); else customModelData = 1;
        if (var.getKeys().contains("Lore")) lore = var.getStringList("Lore"); else lore = new ArrayList<>();
        if (var.getKeys().contains("Enchantments")) {
            enchantments = new HashMap<>();
            for (String data : var.getStringList("Enchantments")) {
                enchantments.put(data.split(":")[0], Integer.parseInt(data.split(":")[1]));
            }
        } else enchantments = new HashMap<>();
        if (var.getKeys().contains("DropPercentage")) dropPercentage = var.getString("DropPercentage"); else dropPercentage = "1/1";
        if (var.getKeys().contains("IsAdditional")) isAdditional = var.getBoolean("IsAdditional"); else isAdditional = false;
    }

    // Getter
    public String getPath() {
        return path;
    }
    public Config getConfig() {
        return Config.loadFromFile(getPath());
    }
    public Material getMaterial() {
        return Material.valueOf(material);
    }
    public String getName() {
        return name;
    }
    public String getLegacy_name() {
        return legacy_name;
    }
    public String getAmount() {
        return amount;
    }
    public int getCustomModelData() {
        return customModelData;
    }
    public List<String> getLore() {
        return lore;
    }
    public Map<Enchantment, Integer> getEnchantments() {
        Map<Enchantment, Integer> value = new HashMap<>();
        for (String var : enchantments.keySet()) {
            value.put(Enchantment.getByName(var.toUpperCase()), enchantments.get(var));
        }
        return value;
    }
    public String getDropPercentage() {
        return dropPercentage;
    }
    public boolean isAdditional() {
        return isAdditional;
    }

}
