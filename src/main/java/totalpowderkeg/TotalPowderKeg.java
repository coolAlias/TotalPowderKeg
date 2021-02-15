package totalpowderkeg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = TotalPowderKeg.ID, name = TotalPowderKeg.NAME, version = TotalPowderKeg.VERSION)
public class TotalPowderKeg
{
	public static final String ID = "totalpowderkeg";
	public static final String NAME = "TPK: Total Powder Keg";
	public static final String VERSION = "1.0";

	@Mod.Instance(TotalPowderKeg.ID)
	public static TotalPowderKeg instance;

	@SidedProxy(clientSide = ID + ".ClientProxy", serverSide = ID + ".ServerProxy")
	public static IProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(TotalPowderKeg.ID);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		proxy.registerModels(event);
	}
}
