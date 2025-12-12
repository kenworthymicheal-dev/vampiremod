package SyncVampirePacket;

import net.micheal.vampiremod.Vampire;
import net.micheal.vampiremod.capability.IVampire;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VampireProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final String KEY = "vampire_data";

    private final Vampire backend = new Vampire();
    private final LazyOptional<IVampire> optional = LazyOptional.of(() -> backend);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapabilities.VAMPIRE_CAP ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putBoolean("IsVampire", backend.isVampire());
        tag.putInt("Blood", backend.getBlood());
        tag.putInt("MaxBlood", backend.getMaxBlood());
        tag.putInt("Level", backend.getLevel());
        tag.putInt("AbilityPoints", backend.getAbilityPoints());

        // Save abilities
        CompoundTag abilities = new CompoundTag();
        for (int i = 0; i < 10; i++) {
            abilities.putBoolean("A" + i, backend.hasAbility(i));
        }
        tag.put("Abilities", abilities);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        backend.setLevel(tag.getInt("Level"));
        backend.setBlood(tag.getInt("Blood"));
        backend.setMaxBlood(tag.getInt("MaxBlood"));
        backend.addAbilityPoints(tag.getInt("AbilityPoints"));
        CompoundTag abilities = tag.getCompound("Abilities");
        for (int i = 0; i < 10; i++) {
            if (abilities.contains("A" + i)) {
                if (abilities.getBoolean("A" + i)) backend.unlockAbility(i);
            }
        }
    }
}
