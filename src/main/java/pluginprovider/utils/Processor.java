package pluginprovider.utils;

import me.devtec.shared.utility.StringUtils;
import me.devtec.theapi.bukkit.game.ItemMaker;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pluginprovider.objects.CachedAttributes;

import java.util.Map;
import java.util.regex.Pattern;

public class Processor {

    // ~Random(min,max)
    public static int parseItemAmount(String text) {
        if (Pattern.compile("~Random\\((.?),(.?)\\)").matcher(text).find()) {
            text = text.replace("~Random(", "").replace(")", "");
            int min = Integer.parseInt(text.split(",")[0]);
            int max = Integer.parseInt(text.split(",")[1]);
            return StringUtils.generateRandomInt(min, max);
        } else return Integer.parseInt(text);
    }

    // Path
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

}