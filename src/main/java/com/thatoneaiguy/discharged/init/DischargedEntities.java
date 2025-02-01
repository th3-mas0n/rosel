package com.thatoneaiguy.discharged.init;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.entity.RoselCrystalPlayerShapeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DischargedEntities {
	public static final EntityType<RoselCrystalPlayerShapeEntity> ROSEL_CRYSTAL_PLAYER_SHAPE_ENTITY_TYPE = Registry.register(
		Registries.ENTITY_TYPE,
		new Identifier(Discharged.MODID, "rosel_crystal_player_shape"),
		FabricEntityTypeBuilder.<RoselCrystalPlayerShapeEntity>create(SpawnGroup.MISC, RoselCrystalPlayerShapeEntity::new)
			.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
			.fireImmune()
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	public static void register() {
		// Entity registration is handled by the static initializer above
	}
}
