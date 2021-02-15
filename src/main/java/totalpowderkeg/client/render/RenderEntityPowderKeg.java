package totalpowderkeg.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import totalpowderkeg.block.ModBlocks;
import totalpowderkeg.entity.EntityPowderKeg;

@SideOnly(Side.CLIENT)
public class RenderEntityPowderKeg extends Render<EntityPowderKeg>
{
	public RenderEntityPowderKeg(RenderManager manager) {
		super(manager);
		this.shadowSize = 0.5F;
	}

	@Override
	public void doRender(EntityPowderKeg entity, double x, double y, double z, float yaw, float partialTicks) {
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x, (float)y + 0.5F, (float)z);
		if ((float)entity.getFuse() - partialTicks + 1.0F < 10.0F) {
			float f = 1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 10.0F;
			f = MathHelper.clamp(f, 0.0F, 1.0F);
			f = f * f;
			f = f * f;
			float f1 = 1.0F + f * 0.3F;
			GlStateManager.scale(f1, f1, f1);
		}
		float f2 = (1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
		this.bindEntityTexture(entity);
		GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
		if (!entity.onGround) {
			float speed = (float)Math.sqrt((entity.motionX * entity.motionX) + (entity.motionY * entity.motionY) + (entity.motionZ * entity.motionZ));
			float df = ((float)entity.ticksExisted + partialTicks);
			float wobble = 5.0F * speed * MathHelper.sin(0.8F * df);
			float tumble = 5.0F * speed * MathHelper.cos(0.8F * df);
			GlStateManager.rotate(wobble, 0.0F, 1.0F, 1.0F);
			GlStateManager.rotate(tumble, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(10.0F * df, -1.0F, 1.0F, 1.0F);
		}
		GlStateManager.translate(-0.5F, -0.5F, 0.5F);
		blockrendererdispatcher.renderBlockBrightness(ModBlocks.POWDER_KEG.getDefaultState(), entity.getBrightness());
		GlStateManager.translate(0.0F, 0.0F, 0.9375F);
		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
			blockrendererdispatcher.renderBlockBrightness(ModBlocks.POWDER_KEG.getDefaultState(), 1.0F);
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		} else if (entity.getFuse() / 5 % 2 == 0) {
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
			GlStateManager.doPolygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			blockrendererdispatcher.renderBlockBrightness(ModBlocks.POWDER_KEG.getDefaultState(), 1.0F);
			GlStateManager.doPolygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPowderKeg entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
