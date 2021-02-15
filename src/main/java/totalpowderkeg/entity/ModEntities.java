package totalpowderkeg.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import totalpowderkeg.TotalPowderKeg;

@ObjectHolder(TotalPowderKeg.ID)
@Mod.EventBusSubscriber(modid = TotalPowderKeg.ID)
public class ModEntities
{
	private static int entityId = 0;

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
		final EntityEntry[] entries = {
				createBuilder("powder_keg").entity(EntityPowderKeg.class).tracker(160, 10, true).build(),
		};
		event.getRegistry().registerAll(entries);
	}

	private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation location = new ResourceLocation(TotalPowderKeg.ID, name);
		return builder.id(location, entityId++).name(location.toString());
	}
}
