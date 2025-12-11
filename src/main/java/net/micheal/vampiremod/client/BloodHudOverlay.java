package net.micheal.vampiremod.client;

import net.micheal.vampiremod.util.capabilityUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BloodHudOverlay implements IGuiOverlay {

    @Override
    public void render(ForgeIngameGui gui, PoseStack pose, float partialTicks, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        int blood = capabilityUtil.CapabilityUtil.getBlood(mc.player);
        int maxBlood = 20;

        int xStart = screenWidth / 2 + 10;  // same position as hunger icons
        int y = screenHeight - 39;

        for (int i = 0; i < maxBlood / 2; i++) {
            int x = xStart + i * 8;

            // empty blood drop
            gui.blit(pose, x, y, 0, 0, 9, 9);

            if (blood / 2 > i) {
                gui.blit(pose, x, y, 9, 0, 9, 9);  // filled blood drop
            }
        }
    }
}



