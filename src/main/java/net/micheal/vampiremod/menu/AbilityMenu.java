package net.micheal.vampiremod.menu;

import net.micheal.vampiremod.init.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class AbilityMenu  extends AbstractContainerMenu {
    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }


    private final ContainerLevelAccess access;
    private final SimpleContainerData data;


    public AbilityMenu(int id, Inventory playerInv) {
        this(id, playerInv, new SimpleContainerData(2));
    }


    public AbilityMenu(int id, Inventory playerInv, SimpleContainerData data) {
        super(ModMenus.ABILITY_MENU.get(), id);
        this.access = ContainerLevelAccess.NULL;
        this.data = data;
        addDataSlots(data);
    }


    // Called when the menu is opened on the client from a packet
    public AbilityMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, new SimpleContainerData(2));
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
