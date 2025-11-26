package net.micheal.vampiremod.base;

import net.micheal.vampiremod.vampiremod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record ModArmormaterials<ingredient>(String name, int duribility, int[] protection, int enchantablity, SoundEvent equipsound, float toughness, float knockbackResitiance, Supplier<Ingredient> repairMaterial) implements ArmorMaterial {
   private static final int[] DURABILITY_PER_SLOT = new int[]{13, 15, 16, 11};
    @Override
    public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
        return DURABILITY_PER_SLOT[slot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.protection[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantablity;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipsound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public @NotNull String getName() {
        return vampiremod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness();
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResitiance;
    }
}
