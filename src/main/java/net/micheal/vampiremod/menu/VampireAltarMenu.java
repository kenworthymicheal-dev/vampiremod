package net.micheal.vampiremod.menu;

import net.micheal.vampiremod.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VampireAltarMenu extends AbstractContainerMenu {

    private final Level level;
    private final ContainerData data;

    // data slots:
    // 0 = current blood
    // 1 = required blood
    // 2 = player level

    public VampireAltarMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainerData(3));
    }

    public VampireAltarMenu(int id, Inventory inv, ContainerData data) {
        super(ModMenus.VAMPIRE_ALTAR_MENU.get(), id);
        this.level = inv.player.level();
        this.data = data;

        addDataSlots(this.data);
    }

    @Override
    public boolean stillValid(Player player) {
        return true; // always valid; altar GUI doesn’t need range check unless you want one
    }

    // ------------------------------------------------
    // BLOOD + LEVEL GETTERS FOR THE SCREEN
    // ------------------------------------------------

    public int getBlood() {
        return data.get(0);
    }

    public int getRequiredBlood() {
        return data.get(1);
    }

    public int getLevel() {
        return data.get(2);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // No slots → no shift-click logic needed
        return ItemStack.EMPTY;
    }
}
