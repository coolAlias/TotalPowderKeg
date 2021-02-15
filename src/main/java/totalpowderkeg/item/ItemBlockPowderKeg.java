package totalpowderkeg.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import totalpowderkeg.entity.EntityPowderKeg;

public class ItemBlockPowderKeg extends ItemBlock
{
	public ItemBlockPowderKeg(Block block) {
		super(block);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.getEntityWorld().isRemote) {
			EntityPowderKeg entity = new EntityPowderKeg(world, player);
			world.spawnEntity(entity);
			world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!player.capabilities.isCreativeMode) {
				stack.shrink(1);
			}
		}
		player.swingArm(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
