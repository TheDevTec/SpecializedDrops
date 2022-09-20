package pluginprovider.selectionsystems;

import me.devtec.shared.dataholder.Config;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pluginprovider.SpecializedDrops;
import pluginprovider.managers.CollectionsSystem;
import pluginprovider.objects.EventInfo;
import pluginprovider.objects.Profile;

import java.util.ArrayList;
import java.util.List;

public class OverrideSystem {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Builder
    public static void reloadOverrides() {
        if (!SpecializedDrops.getOverrides().getBoolean("Enabled")) return;
        Config var = SpecializedDrops.getOverrides();
        for (String overrideName : var.getKeys("Configuration/Overrides")) {
            if (var.getBoolean("Overrides." + overrideName + ".Enabled")) {
                String profile = var.getString("Overrides." + overrideName + ".Profile");
                String collection = var.getString("Overrides." + overrideName + ".Collection");
                overrides.add(new OverrideSystem(overrideName, new Profile(profile), CollectionsSystem.getCollectionByName(collection)));
            }
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // PublicAPI
    private static final List<OverrideSystem> overrides = new ArrayList<>();
    public static boolean override(EventInfo info) {
        if (!SpecializedDrops.getOverrides().getBoolean("Enabled")) return false;
        boolean value = false;
        for (OverrideSystem check : overrides) {
            if (check.getProfile().asyncCheckWith(info)) {
                value = true;
                check.getGroup().asyncPickRandomItems(info.getDefaultDrop());
            }
        }
        return value;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Values
    private final String name;
    private final Profile profile;
    private final CollectionsSystem group;

    // Constructor
    public OverrideSystem(String name, Profile profile, CollectionsSystem group) {
        this.name = name;
        this.profile = profile;
        this.group = group;
    }

    // Getter
    public String getName() {
        return name;
    }
    public CollectionsSystem getGroup() {
        return group;
    }
    public Profile getProfile() {
        return profile;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}
