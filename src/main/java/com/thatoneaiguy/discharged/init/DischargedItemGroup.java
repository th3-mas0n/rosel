package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DischargedItemGroup {
	public static final ItemGroup ROSEL_MAIN_ITEM_GROUP =
		FabricItemGroupBuilder.create(new Identifier(Discharged.MODID, "dischargedmain"))
			.icon(() -> new ItemStack(DischargedItems.ROSEL_SHARD)) // Use a Supplier<ItemStack> for the icon
			.displayName(Text.literal("Discharged")) // Use displayName instead of displayText
			.entries((enabledFeatures, entries, operatorEnabled) -> {
				entries.add(new ItemStack(DischargedItems.ROSEL_KOPIS));
				//entries.add(new ItemStack(DischargedItems.ROSEL_DISC));
				entries.add(new ItemStack(DischargedItems.ROSEL_GAUNTLET));
				entries.add(new ItemStack(DischargedItems.ROSEL_SHARD));
				entries.add(new ItemStack(DischargedBlocks.PERFECT_ROSEL));
				entries.add(new ItemStack(DischargedBlocks.RAW_ROSEL));
				entries.add(new ItemStack(DischargedBlocks.ROUGH_ROSEL));
			})
			.build();

	public static void register() {
		// No additional registration logic needed for item groups in Fabric
	}
}
