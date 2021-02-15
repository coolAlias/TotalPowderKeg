package totalpowderkeg;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import totalpowderkeg.block.ModBlocks;
import totalpowderkeg.client.render.RenderEntityPowderKeg;
import totalpowderkeg.entity.EntityPowderKeg;

public class ClientProxy implements IProxy
{
	@Override
	public void preInit() {
	}

	@Override
	public void init() {
	}

	@Override
	public void postInit() {
	}

	@Override
	public void registerModels(ModelRegistryEvent event) {
		Item item = Item.getItemFromBlock(ModBlocks.POWDER_KEG);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPowderKeg.class, manager -> new RenderEntityPowderKeg(manager));
	}
}
