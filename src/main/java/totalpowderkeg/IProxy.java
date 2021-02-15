package totalpowderkeg;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{
	/** Called during {@link FMLPreInitializationEvent} */
	public void preInit();

	/** Called during {@link FMLInitializationEvent} */
	public void init();

	/** Called during {@link FMLPostInitializationEvent} */
	public void postInit();

	/** Called from main mod event handler to delegate to proxy */
	public void registerModels(ModelRegistryEvent event);

}
