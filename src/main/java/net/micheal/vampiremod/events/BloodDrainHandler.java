package net.micheal.vampiremod.events;

import net.micheal.vampiremod.Vampire;
import net.micheal.vampiremod.capability.IVampire;
import net.micheal.vampiremod.capability.ModCapabilities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "yourmodid", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BloodDrainHandler {



    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        // 1.19.2 correct access
        Level level = player.getLevel();

        // Only server-side
        if (level.isClientSide()) return;


        player.getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent((IVampire vamp) -> {
            if (!vamp.isVampire()) return;

            // ... your logic here ...
        });

            // Run every 20 ticks = 1 second
            if (player.tickCount % 20 != 0) return;

            int radius = 3;

            // AABB works the same in Java 17
            AABB box = player.getBoundingBox().inflate(radius);

            var mobs = level.getEntitiesOfClass(
                    LivingEntity.class,
                    box,
                    e -> e != player && e.isAlive()
            );

            if (mobs.isEmpty()) return;

            LivingEntity target = mobs.get(0);

            // Add blood
        player.getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent(vamp -> {
            if (!vamp.isVampire()) return;

            int newBlood = vamp.getBlood() + 2;
            vamp.setBlood(newBlood);
        });

            // Damage mob (correct damage source for 1.19.x)
        target.hurt(net.minecraft.world.damagesource.DamageSource.playerAttack(player), 1.0F);

            // Sound
            level.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.PLAYER_BREATH,
                    player.getSoundSource(),
                    1f,
                    1f
            );

            // Particle
            level.addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    target.getX(),
                    target.getY() + 1,
                    target.getZ(),
                    0, 0.05, 0
            );
        }
    }

