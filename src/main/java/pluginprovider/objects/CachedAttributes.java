package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

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
    private final String dropPercentage;
    private final boolean isAdditional;

    // Constructor
    public CachedAttributes(String path) {
        Config var = Config.loadFromFile(path);
        material = var.getString("Material");
        name = var.getString("Name");
        legacy_name = var.getString("LegacyName");
        amount = var.getString("Amount");
        customModelData = var.getInt("CustomModelData");
        lore = var.getStringList("Lore");
        //
        enchantments = new HashMap<>();
        for (String data : var.getStringList("Enchantments")) {
            enchantments.put(data.split(":")[0], Integer.parseInt(data.split(":")[1]));
        }
        //
        dropPercentage = var.getString("DropPercentage");
        isAdditional = var.getBoolean("IsAdditional");
    }

    // Getter
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
