package net.micheal.vampiremod.event;

import net.micheal.vampiremod.util.capabilityUtil;
import net.micheal.vampiremod.vampiremod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.micheal.vampiremod.util.vampireprovider
import net.minecraft.world.entity.Entity;


public class commonEvents {
    @SubscribeEvent
    public void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof net.minecraft.world.entity.player.Player) {
            event.addCapability(new ResourceLocation(vampiremod.MOD_ID, "vampire"),
                    new vampireprovider());
            @SubscribeEvent
            public void onFood(FoodLevelChangeEvent event) {
                if (event.getEntity() instanceof Player player) {
                    if (capabilityUtil.CapabilityUtil.isVampire(player)) {
                        event.setCanceled(true);
        }
    }
}