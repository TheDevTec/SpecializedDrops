package pluginprovider.utils;

import me.devtec.theapi.bukkit.xseries.XMaterial;
import org.bukkit.Material;
import pluginprovider.enums.ProvidedBlockDropType;

import java.util.HashMap;
import java.util.Map;

public class AdvancedTypes {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /*
    * Source: https://minecraft.fandom.com/wiki/Block
    * */

    // Builder
    public static void loadTypes() {
        // A
        cache.put(XMaterial.ACACIA_BUTTON.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.ACACIA_DOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_FENCE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_LEAVES.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.ACACIA_LOG.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_PLANKS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_PRESSURE_PLATE.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.ACACIA_SAPLING.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.ACACIA_SIGN.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_SLAB.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_STAIRS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_TRAPDOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACACIA_WOOD.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.ACTIVATOR_RAIL.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.ALLIUM.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.AMETHYST_CLUSTER.name(), ProvidedBlockDropType.ORE_TYPE);
        cache.put(XMaterial.ANCIENT_DEBRIS.name(), ProvidedBlockDropType.ORE_TYPE);
        cache.put(XMaterial.ANDESITE.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.ANDESITE_SLAB.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.ANDESITE_STAIRS.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.ANDESITE_WALL.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.ANVIL.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.AZALEA.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.AZALEA_LEAVES.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.AZURE_BLUET.name(), ProvidedBlockDropType.PLANTS_TYPE);
        // B
        cache.put(XMaterial.BARREL.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BARRIER.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.BASALT.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.BEACON.name(), ProvidedBlockDropType.ADVANTAGE_TYPE);
        cache.put(XMaterial.BEDROCK.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.BEE_NEST.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BEEHIVE.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BEETROOTS.name(), ProvidedBlockDropType.FOOD_TYPE);
        cache.put(XMaterial.BELL.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BIG_DRIPLEAF.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BIRCH_BUTTON.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.BIRCH_DOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_FENCE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_FENCE_GATE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_LEAVES.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BIRCH_LOG.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_PLANKS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_PRESSURE_PLATE.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.BIRCH_SAPLING.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BIRCH_SIGN.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_SLAB.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_STAIRS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_TRAPDOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BIRCH_WOOD.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.BLACK_BANNER.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLACK_BED.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BLACK_CONCRETE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLACK_CONCRETE_POWDER.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.BLACK_GLAZED_TERRACOTTA.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLACK_SHULKER_BOX.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BLACK_STAINED_GLASS.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BLACK_STAINED_GLASS_PANE.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BLACK_TERRACOTTA.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BLACK_WOOL.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLACKSTONE.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.BLACKSTONE_SLAB.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.BLACKSTONE_STAIRS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.BLACKSTONE_WALL.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.BLAST_FURNACE.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.AMETHYST_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.COAL_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.COPPER_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.DIAMOND_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.EMERALD_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.GOLD_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.IRON_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.LAPIS_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.NETHERITE_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.QUARTZ_BLOCK.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.RAW_COPPER_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.RAW_GOLD_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.RAW_IRON_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.REDSTONE_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.BLUE_BANNER.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLUE_BED.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BLUE_CANDLE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLUE_CARPET.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLUE_CONCRETE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLUE_CONCRETE_POWDER.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.BLUE_GLAZED_TERRACOTTA.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BLUE_ICE.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BLUE_ORCHID.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BLUE_SHULKER_BOX.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BLUE_STAINED_GLASS.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BLUE_STAINED_GLASS_PANE.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BLUE_TERRACOTTA.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BONE_BLOCK.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BOOKSHELF.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BRAIN_CORAL.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BRAIN_CORAL_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.BRAIN_CORAL_FAN.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BREWING_STAND.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BRICK_SLAB.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BRICK_STAIRS.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BRICK_WALL.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BRICKS.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BROWN_BANNER.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BROWN_BED.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BROWN_CANDLE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BROWN_CARPET.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BROWN_CONCRETE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BROWN_CONCRETE_POWDER.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.BROWN_GLAZED_TERRACOTTA.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BROWN_MUSHROOM.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BROWN_SHULKER_BOX.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.BROWN_STAINED_GLASS.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BROWN_STAINED_GLASS_PANE.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.BROWN_TERRACOTTA.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.BROWN_WOOL.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.BUBBLE_CORAL.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BUBBLE_CORAL_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.BUBBLE_CORAL_FAN.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.BUDDING_AMETHYST.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        // C
        cache.put(XMaterial.CACTUS.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CAKE.name(), ProvidedBlockDropType.FOOD_TYPE);
        cache.put(XMaterial.CALCITE.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.CAMPFIRE.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CANDLE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CARROTS.name(), ProvidedBlockDropType.FOOD_TYPE);
        cache.put(XMaterial.CARTOGRAPHY_TABLE.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CARVED_PUMPKIN.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CAULDRON.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CAVE_VINES.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CHAIN.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CHAIN_COMMAND_BLOCK.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.CHEST.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CHIPPED_ANVIL.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CHISELED_DEEPSLATE.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CHISELED_NETHER_BRICKS.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CHISELED_POLISHED_BLACKSTONE.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CHISELED_QUARTZ_BLOCK.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CHISELED_RED_SANDSTONE.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CHISELED_SANDSTONE.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CHISELED_STONE_BRICKS.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.CHORUS_FLOWER.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CHORUS_PLANT.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CLAY.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.COAL_ORE.name(), ProvidedBlockDropType.ORE_TYPE);
        cache.put(XMaterial.COARSE_DIRT.name(), ProvidedBlockDropType.DIRT_TYPE);
        cache.put(XMaterial.COBBLED_DEEPSLATE.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.COBBLED_DEEPSLATE_SLAB.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.COBBLED_DEEPSLATE_STAIRS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.DEEPSLATE_BRICK_WALL.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.COBBLESTONE.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.COBBLESTONE_SLAB.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.COBBLESTONE_STAIRS.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.COBBLESTONE_WALL.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.COBWEB.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.COCOA.name(), ProvidedBlockDropType.FOOD_TYPE);
        cache.put(XMaterial.COMMAND_BLOCK.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.COMPOSTER.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CONDUIT.name(), ProvidedBlockDropType.UNDEFINED_TYPE);
        cache.put(XMaterial.COPPER_ORE.name(), ProvidedBlockDropType.ORE_TYPE);
        cache.put(XMaterial.CORNFLOWER.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CRACKED_DEEPSLATE_BRICKS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CRACKED_DEEPSLATE_TILES.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CRACKED_NETHER_BRICKS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CRACKED_POLISHED_BLACKSTONE_BRICKS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CRACKED_STONE_BRICKS.name(), ProvidedBlockDropType.DEEP_STONE_TYPE);
        cache.put(XMaterial.CRAFTING_TABLE.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CREEPER_HEAD.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CRIMSON_BUTTON.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.CRIMSON_DOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_FENCE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_FENCE_GATE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_FUNGUS.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CRIMSON_HYPHAE.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_NYLIUM.name(), ProvidedBlockDropType.DIRT_TYPE);
        cache.put(XMaterial.CRIMSON_PLANKS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_PRESSURE_PLATE.name(), ProvidedBlockDropType.ELECTRICITY_TYPE);
        cache.put(XMaterial.CRIMSON_ROOTS.name(), ProvidedBlockDropType.PLANTS_TYPE);
        cache.put(XMaterial.CRIMSON_SIGN.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_SLAB.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_STAIRS.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_STEM.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRIMSON_TRAPDOOR.name(), ProvidedBlockDropType.WOOD_TYPE);
        cache.put(XMaterial.CRYING_OBSIDIAN.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CUT_COPPER.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CUT_COPPER_SLAB.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CUT_COPPER_STAIRS.name(), ProvidedBlockDropType.BASED_BLOCK_TYPE);
        cache.put(XMaterial.CUT_RED_SANDSTONE.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CUT_RED_SANDSTONE_SLAB.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CUT_SANDSTONE.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CUT_SANDSTONE_SLAB.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CYAN_BANNER.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CYAN_BED.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CYAN_CARPET.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CYAN_CARPET.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CYAN_CONCRETE.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CYAN_CONCRETE_POWDER.name(), ProvidedBlockDropType.SAND_TYPE);
        cache.put(XMaterial.CYAN_GLAZED_TERRACOTTA.name(), ProvidedBlockDropType.DESIGN_TYPE);
        cache.put(XMaterial.CYAN_SHULKER_BOX.name(), ProvidedBlockDropType.USING_TYPE);
        cache.put(XMaterial.CYAN_STAINED_GLASS.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.CYAN_STAINED_GLASS_PANE.name(), ProvidedBlockDropType.GLASS_TYPE);
        cache.put(XMaterial.CYAN_TERRACOTTA.name(), ProvidedBlockDropType.STONE_TYPE);
        cache.put(XMaterial.CYAN_WOOL.name(), ProvidedBlockDropType.DESIGN_TYPE);
        // D
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Public API
    private static final Map<String, ProvidedBlockDropType> cache = new HashMap<>();
    public static ProvidedBlockDropType getByMaterial(Material material) {
        return cache.get(XMaterial.matchXMaterial(material).name());
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////


}
