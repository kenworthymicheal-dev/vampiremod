package net.micheal.vampiremod.events;

import SyncVampirePacket.VampireProvider;
import net.micheal.vampiremod.vampiremod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = vampiremod.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(vampiremod.MOD_ID, "vampire"),
                    new VampireProvider());
        }
    }

    @SubscribeEvent
    public static void cloneCaps(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();

        event.getOriginal().getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent(oldCap -> {
            event.getEntity().getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent(newCap -> {
                CompoundTag tag = new CompoundTag();
                oldCap.saveNBTData(tag);
                newCap.loadNBTData(tag);
            });
        });
    }
}