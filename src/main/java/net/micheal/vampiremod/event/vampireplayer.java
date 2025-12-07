package net.micheal.vampiremod.event;

import com.mojang.datafixers.types.templates.List;
import net.minecraft.client.Minecraft;

public class vampireplayer {

    public class VampireAbilitiesScreen extends AbstractContainerScreen<VampireAbilitiesMenu> {

        private final Minecraft mc = Minecraft.getInstance();
        private final List<String> abilities = List.of("shadow_step", "sanguine_strike", "blood_mist", "dominate_will");

        public VampireAbilitiesScreen(VampireAbilitiesMenu menu, Inventory inv, Component title) {
            super(menu, inv, title);
            this.imageWidth = 176;
            this.imageHeight = 166;
        }

        @Override
        protected void init() {
            super.init();
            // place a button per ability that attempts to spend a point to unlock it
            int y = this.topPos + 20;
            for (int i = 0; i < abilities.size(); i++) {
                String id = abilities.get(i);
                int idx = i;
                this.addRenderableWidget(new Button(this.leftPos + 10, y + i * 22, 150, 20,
                        Component.literal((VampireData.hasAbility(mc.player, id) ? "[UNLOCKED] " : "") + id),
                        btn -> {
                            // send packet to server to spend point for this ability
                            VampirismMod.CHANNEL.sendToServer(new SpendPointPacket(id));
                        }));
            }
        }

        @Override
        public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
            // background shading
            renderBackground(poseStack);
            super.render(poseStack, mouseX, mouseY, partialTicks);
            // draw ability points and xp/level
            int pts = VampireData.getAbilityPoints(mc.player);
            double xp = VampireData.getXp(mc.player);
            int stage = mc.player.getPersistentData().getCompound("vampirism").getInt("stage");
            drawString(poseStack, this.font, "Vampire Stage: " + stage, leftPos + 10, topPos + 6, 0xFFFFFF);
            drawString(poseStack, this.font, "Vampire XP: " + (int) xp, leftPos + 110, topPos + 6, 0xAAAAAA);
            drawString(poseStack, this.font, "Ability Points: " + pts, leftPos + 10, topPos + 150, 0xFF3333);
        }

        @Override
        protected void renderBg(PoseStack poseStack, float v, int i, int j) {
            // no textured background provided - fill with a dark rectangle if desired (left out for brevity)
        }
    }





}
