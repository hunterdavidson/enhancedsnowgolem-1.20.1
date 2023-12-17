package net.karrot.enhancedsnowgolem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.karrot.enhancedsnowgolem.EnhancedSnowGolem;
import net.karrot.enhancedsnowgolem.item.custom.PackedSnowballItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PACKED_SNOWBALL = registerItem("packed_snowball", new PackedSnowballItem(new FabricItemSettings()));

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(PACKED_SNOWBALL);
    }

    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, new Identifier(EnhancedSnowGolem.MOD_ID, name), item);
    }

    public static void registerModItems() {

        EnhancedSnowGolem.LOGGER.info("Registering Mod Items for " + EnhancedSnowGolem.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
    }
}
