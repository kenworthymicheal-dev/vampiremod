package net.micheal.vampiremod.init;

import net.micheal.vampiremod.base.ModArmormaterials;
import net.micheal.vampiremod.vampiremod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class iteminit {
   public static  final DeferredRegister<Item> Item = DeferredRegister.create(ForgeRegistries.ITEMS, vampiremod.MOD_ID);

   public static final RegistryObject<Item> vampire_gem = Item.register("vampire_gem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<ArmorItem> vampire_madalloin = Item.register("vampire_medallion", () -> new ArmorItem(ArmorMaterials.GOLD, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));


    private static ArmorMaterial ArmorTiers;
    public static final RegistryObject<Item> gold_chain = Item.register("gold_chain", () -> new ArmorItem(ArmorMaterials.GOLD, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));





}


