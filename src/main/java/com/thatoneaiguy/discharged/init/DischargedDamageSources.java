package com.thatoneaiguy.discharged.init;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DischargedDamageSources {
	// Registry keys for custom damage types
	public static final RegistryKey<DamageType> INSTAKILL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("discharged", "instakill"));
	public static final RegistryKey<DamageType> CRYSTALISED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("discharged", "crystalised"));
	public static final RegistryKey<DamageType> CONDUCTED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("discharged", "conducted"));

	// Method to create a DamageSource from a DamageType key
	public static DamageSource of(World world, RegistryKey<DamageType> key) {
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}

	public static void register() {
		// No explicit registration is needed for damage types in 1.20.1
		// Damage types are registered via JSON files in the data pack
	}
}
