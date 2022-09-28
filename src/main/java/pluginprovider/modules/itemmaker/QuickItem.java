package pluginprovider.modules.itemmaker;

import me.devtec.shared.dataholder.Config;
import me.devtec.theapi.bukkit.game.ItemMaker;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginprovider.utils.Processor;
import pluginprovider.utils.WriterResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickItem {

    // Values
    private Material material;
    private String name, legacy_name, amount;
    private int custom_model_data;
    private List<String> lore;
    private Map<String, Integer> enchantments;
    private double drop_percentage;
    private boolean is_additional;

    // Constructor
    private QuickItem(QuickItem... item) {
        if (item.length > 0) {
            QuickItem rewrite = item[0];
            material = rewrite.getMaterial();
            name = rewrite.getName();
            legacy_name = rewrite.getLegacyName();
            amount = rewrite.getAmount();
            custom_model_data = rewrite.getCustomModelData();
            lore = rewrite.getLore();
            enchantments = rewrite.getEnchantments();
            drop_percentage = rewrite.getDropPercentage();
            is_additional = rewrite.IsAdditional();
        }
    }

    // Builder
    public static QuickItem generate() {
        return new QuickItem();
    }
    public static QuickItem generate(QuickItem item) {
        return new QuickItem(item);
    }

    // Optionals
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setCustomModelData(int custom_model_data) {
        this.custom_model_data = custom_model_data;
    }
    public void setDropPercentage(double drop_percentage) {
        this.drop_percentage = drop_percentage;
    }
    public void addEnchantment(Enchantment enchantment, Integer power) {
        if (enchantments == null) enchantments = new HashMap<>();
        enchantments.put(enchantment.getName(), power);
    }
    public void setIsAdditional(boolean is_additional) {
        this.is_additional = is_additional;
    }
    public void setLegacyName(String legacy_name) {
        this.legacy_name = legacy_name;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    // Getter
    public String getName() {
        if (name == null) return "";
        return name;
    }
    public String getAmount() {
        if (amount == null) return "1";
        return amount;
    }
    public Map<String, Integer> getEnchantments() {
        if (enchantments == null) return new HashMap<>();
        return enchantments;
    }
    public List<String> getLore() {
        if (lore == null) return new ArrayList<>();
        return lore;
    }
    public String getLegacyName() {
        if (legacy_name == null) return "";
        return legacy_name;
    }
    public Material getMaterial() {
        return material;
    }
    public double getDropPercentage() {
        return drop_percentage;
    }
    public int getCustomModelData() {
        return custom_model_data;
    }
    public boolean IsAdditional() {
        return is_additional;
    }

    // Build item
    public ItemStack getFormatted(Player opener) {
        ItemMaker prepare = Processor.prepareMaker(material.name().toUpperCase(), opener)
                .displayName(getName())
                .customModel(getCustomModelData())
                .amount(Processor.parseItemAmount(getAmount()))
                .lore(getLore());
        for (String var : getEnchantments().keySet()) {
            Enchantment enchantment = Enchantment.getByName(var);
            if (enchantment == null) continue;
            prepare.enchant(enchantment, getEnchantments().get(var));
        }
        return prepare.build();
    }

    // Writer
    public WriterResult write(Config selectedFile) {
        if (isFileInvalid(selectedFile)) return WriterResult.INVALID_FILE;
        try {
            selectedFile.set("Material", getMaterial());
            selectedFile.set("Name", getName());
            selectedFile.set("LegacyName", getLegacyName());
            selectedFile.set("Amount", getAmount());
            selectedFile.set("CustomModelData", getCustomModelData());
            selectedFile.set("Lore", getLore());
            selectedFile.set("Enchantments", processEnchantments());
            selectedFile.set("DropPercentage", getDropPercentage());
            selectedFile.set("IsAdditional", IsAdditional());
            selectedFile.save();
            return WriterResult.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return WriterResult.UNEXPECTED_ERROR;
        }
    }

    // Depends
    private boolean isFileInvalid(Config selectedFile) {
        return (selectedFile == null || selectedFile.getFile() == null);
    }
    private List<String> processEnchantments() {
        List<String> value = new ArrayList<>();
        for (String var : getEnchantments().keySet()) {
            value.add(var + ":" + getEnchantments().get(var));
        }
        return value;
    }

}