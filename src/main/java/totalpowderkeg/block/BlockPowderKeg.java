package totalpowderkeg.block;

import net.minecraft.block.BlockTNT;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import totalpowderkeg.entity.EntityPowderKeg;

public class BlockPowderKeg extends BlockTNT
{
	private static final AxisAlignedBB KEG_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.875D, 0.9375D, 0.875D);

	public BlockPowderKeg() {
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return KEG_AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) {
		if (!world.isRemote) {
			EntityPowderKeg entity = new EntityPowderKeg(world, ((float)pos.getX() + 0.5F), pos.getY(), ((float)pos.getZ() + 0.5F), explosion.getExplosivePlacedBy());
			entity.setFuse(world.rand.nextInt(entity.getFuse() / 4) + entity.getFuse() / 8);
			world.spawnEntity(entity);
		}
	}

	@Override
	public void explode(World world, BlockPos pos, IBlockState state, EntityLivingBase igniter) {
		if (!world.isRemote && state.getValue(EXPLODE)) {
			EntityPowderKeg entity = new EntityPowderKeg(world, ((float)pos.getX() + 0.5F), pos.getY(), ((float)pos.getZ() + 0.5F), igniter);
			world.spawnEntity(entity);
			world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}
}
