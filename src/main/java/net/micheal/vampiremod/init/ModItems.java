package net.micheal.vampiremod.init;

import net.micheal.vampiremod.vampiremod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, vampiremod.MOD_ID);

    public static final RegistryObject<Item> GOLD_CHAIN = ITEMS.register("gold_chain",
            () -> new GoldChain(new Item.Properties().tab(=CreativeModeTab.TAB_MATERIALS).stacksTo(1)));

    public static final RegistryObject<Item> VAMPIRE_MEDALLION = ITEMS.register("vampire_medallion",
            () -> new VampireMedallion(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));

    public static final RegistryObject<Item> BLOOD_BOTTLE = ITEMS.register("blood_bottle",
            () -> new BloodBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).stacksTo(16).food(null)));

    public static void register(IEventBus bus) { ITEMS.register(bus); }



}
