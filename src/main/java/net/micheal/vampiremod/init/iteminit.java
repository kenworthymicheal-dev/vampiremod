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


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, vampiremod.MOD_ID);
    public static final DeferredRegister<net.minecraft.world.level.block.Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, vampiremod.MOD_ID);

    public static final RegistryObject<Item> BLOOD_VIAL = ITEMS.register("blood_vial",
            () -> new Item(new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_MISC)));



    public static final RegistryObject<Item> SHADOW_TALISMAN = ITEMS.register("shadow_talisman",
            () -> new ShadowTalismanItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> SANGUINE_DAGGER = ITEMS.register("sanguine_dagger",
            () -> new SanguineDaggerItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<net.minecraft.world.level.block.Block> VAMPIRE_ALTAR = BLOCKS.register("vampire_altar",
            () -> new VampireAltarBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE).strength(3.5f)));

    private static ArmorMaterial ArmorTiers;
    public static final RegistryObject<Item> gold_chain = Item.register("gold_chain", () -> new ArmorItem(ArmorMaterials.GOLD, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));





}


