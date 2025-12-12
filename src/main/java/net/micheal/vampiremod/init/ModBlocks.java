package net.micheal.vampiremod.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VampireMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VampireMod.MODID);

    public static final RegistryObject<Block> VAMPIRE_ALTAR = BLOCKS.register("vampire_altar",
            () -> new VampireAltarBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).sound(SoundType.STONE)));

    public static final RegistryObject<BlockEntityType<VampireAltarBlockEntity>> VAMPIRE_ALTAR_BE = TILE_ENTITIES.register("vampire_altar",
            () -> BlockEntityType.Builder.of(VampireAltarBlockEntity::new, VAMPIRE_ALTAR.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        TILE_ENTITIES.register(bus);
    }
}
