package net.micheal.vampiremod.init;

import net.micheal.vampiremod.menu.VampireAltarMenu;
import net.micheal.vampiremod.vampiremod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, vampiremod.MOD_ID);

    public static final RegistryObject<MenuType<VampireAltarMenu>> VAMPIRE_ALTAR_MENU = MENUS.register("vampire_altar",
            () -> new MenuType<>((windowId, inv, data) -> new VampireAltarMenu(windowId, inv, (net.micheal.vampiremod.block.entity.VampireAltarBlockEntity) null)));

    public static void register(IEventBus bus) { MENUS.register(bus); }
}
}
