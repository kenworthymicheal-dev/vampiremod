package net.micheal.vampiremod.Menu;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class vampireMenuScreen {
    protected VampireMenuScreen() {
        super(Component.literal("Vampire Menu"));
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float pt) {
        this.renderBackground(gfx);

        Minecraft mc = Minecraft.getInstance();
        Player p = mc.player;

        p.getCapability(VampireDataProvider.VAMP_DATA).ifPresent(vamp -> {
            gfx.drawString(mc.font, "Blood: " + vamp.getBlood(), width / 2 - 40, height / 2 - 20, 0xFF0000);
            gfx.drawString(mc.font, "Level: " + vamp.getLevel(), width / 2 - 40, height / 2, 0xFFFFFF);
        });

        super.render(gfx, mouseX, mouseY, pt);
    }
}}
