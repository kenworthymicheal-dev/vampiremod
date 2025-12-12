package SyncVampirePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class SyncVampirePacket {
    private final boolean vampire;
    private final int level;
    private final int blood;
    private final int maxBlood;
    private final int abilityPoints;

    public SyncVampirePacket(boolean vampire, int level, int blood, int maxBlood, int abilityPoints) {
        this.vampire = vampire;
        this.level = level;
        this.blood = blood;
        this.maxBlood = maxBlood;
        this.abilityPoints = abilityPoints;
    }

    public SyncVampirePacket(FriendlyByteBuf buf) {
        vampire = buf.readBoolean();
        level = buf.readInt();
        blood = buf.readInt();
        maxBlood = buf.readInt();
        abilityPoints = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(vampire);
        buf.writeInt(level);
        buf.writeInt(blood);
        buf.writeInt(maxBlood);
        buf.writeInt(abilityPoints);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(VampireCapability.INSTANCE).ifPresent(cap -> {
                cap.setVampire(vampire);
                cap.setLevel(level);
                cap.setBlood(blood);
                cap.setMaxBlood(maxBlood);
                cap.addAbilityPoint(abilityPoints);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
