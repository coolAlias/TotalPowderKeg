package totalpowderkeg.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import totalpowderkeg.TotalPowderKeg;
import totalpowderkeg.entity.EntityPowderKeg;
import totalpowderkeg.item.ItemBlockPowderKeg;

@ObjectHolder(TotalPowderKeg.ID)
@Mod.EventBusSubscriber(modid = TotalPowderKeg.ID)
public class ModBlocks
{
	public static final BlockPowderKeg POWDER_KEG = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final Block[] blocks = {
				new BlockPowderKeg().setRegistryName(TotalPowderKeg.ID, "powder_keg").setTranslationKey(TotalPowderKeg.ID + ":powder_keg"),
		};
		event.getRegistry().registerAll(blocks);
	}

	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
		final Item[] items = {
				new ItemBlockPowderKeg(POWDER_KEG).setRegistryName(POWDER_KEG.getRegistryName()),
		};
		event.getRegistry().registerAll(items);
		registerDispenseBehaviors();
	}

	private static void registerDispenseBehaviors() {
		Item item = Item.getItemFromBlock(POWDER_KEG);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, new BehaviorDefaultDispenseItem()
		{
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				World world = source.getWorld();
				BlockPos pos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));
				EntityPowderKeg entity = new EntityPowderKeg(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, null);
				world.spawnEntity(entity);
				world.playSound((EntityPlayer)null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
				stack.shrink(1);
				return stack;
			}
		});
	}
}
