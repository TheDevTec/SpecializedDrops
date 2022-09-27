package pluginprovider.modules;

import pluginprovider.modules.itemmaker.SpecializedItemMaker;

public class SpecializedModuleManager {

    // Modules
    private final SpecializedItemMaker itemMaker;

    // Constructor
    public SpecializedModuleManager() {
        this.itemMaker = new SpecializedItemMaker();
    }

    // Getters
    public SpecializedItemMaker getItemMaker() {
        return itemMaker;
    }

}
