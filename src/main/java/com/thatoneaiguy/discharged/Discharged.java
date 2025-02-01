package com.thatoneaiguy.discharged;

import com.thatoneaiguy.discharged.cca.DiscComponent;
import com.thatoneaiguy.discharged.cca.KopisComponent;
import com.thatoneaiguy.discharged.cca.RoselCoatingComponent;
import com.thatoneaiguy.discharged.entity.DiscEntity;
import com.thatoneaiguy.discharged.entity.RoselCrystalPlayerShapeEntity;
import com.thatoneaiguy.discharged.init.*;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class Discharged implements ModInitializer, EntityComponentInitializer {
	public static final String MODID = "discharged";
	public static int ticks = -1;
	public static final Logger LOGGER = LoggerFactory.getLogger("Discharged");

	private static <T extends Entity> EntityType<T> registerEntityType(String name, SpawnGroup group, EntityType.EntityFactory<T> entityFactory, float width, float height) {
		Identifier entityId = new Identifier(MODID, name);
		EntityType<T> entityType = EntityType.Builder.create(entityFactory, group)
			.setDimensions(width, height)
			.maxTrackingRange(4)
			.trackingTickInterval(10)
			.build(entityId.toString());
		return Registry.register(Registries.ENTITY_TYPE, entityId, entityType);
	}

	public static final Identifier MODE_CHANGE_PACKET = new Identifier(MODID, "mode_change");
	public static final Identifier SYNC_ITEM_MODE_PACKET = new Identifier(MODID, "sync_item_mode");

	// CCA
	public static final ComponentKey<RoselCoatingComponent> ROSEL_COATING_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_coating"), RoselCoatingComponent.class);
	public static final ComponentKey<KopisComponent> ROSEL_KOPIS_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_kopis"), KopisComponent.class);
	public static final ComponentKey<DiscComponent> ROSEL_DISC_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_disc"), DiscComponent.class);

	@Override
	public void onInitialize() {
		GeckoLib.initialize();

		DischargedItems.register();
		DischargedItemGroup.register();
		DischargedBlocks.registerAll();
		DischargedSounds.registerSounds();
		DischargedLodestoneParticles.init();
		DischargedEnchantments.register();
		DischargedDamageSources.register();

		MidnightConfig.init("discharged", DischargedConfig.class);

		UseEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
			if (entity instanceof RoselCrystalPlayerShapeEntity) {
				if (!player.isHolding(DischargedItems.ROSEL_SHARD)) {
					return ActionResult.FAIL;
				}
				return ActionResult.CONSUME;
			}
			return ActionResult.FAIL;
		});

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(this::onLivingEntityDamage);
	}

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(PlayerEntity.class, ROSEL_COATING_COMPONENT, RoselCoatingComponent::new);
		registry.registerFor(PlayerEntity.class, ROSEL_KOPIS_COMPONENT, KopisComponent::new);
		registry.registerFor(DiscEntity.class, ROSEL_DISC_COMPONENT, DiscComponent::new);
	}

	private boolean onLivingEntityDamage(LivingEntity entity, DamageSource source, float amount) {
		return RoselGauntletItem.handleDamageReflection(entity, source, amount);
	}

	public static Identifier id(String name) {
		return new Identifier("discharged", name);
	}
}
