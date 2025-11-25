package net.micheal.vampiremod.init;

import net.micheal.vampiremod.vampiremod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class iteminit {
   public static  final DeferredRegister<Item> Item = DeferredRegister.create(ForgeRegistries.ITEMS, vampiremod.MOD_ID);

   public static final RegistryObject<Item> vampire_gem = Item.register("vampire gem_item", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Item> vampire_madalloin = Item.register("vampire_medallion", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));



    public static final RegistryObject<Item> gold_chain = Item.register("gold_chain", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));



}

