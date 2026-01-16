package cz.kajacx.circuitnetworkmc.block;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import cz.kajacx.circuitnetworkmc.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CircuitNetworkMC.MOD_ID);

    public static final String CONSTANT_COMBINATOR_BLOCK_NAME = "constant_combinator";
    @SuppressWarnings("null")
    public static final @Nonnull DeferredBlock<Block> CONSTANT_COMBINATOR_BLOCK = registerBlock(
            CONSTANT_COMBINATOR_BLOCK_NAME, () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));

    public static void register(@Nonnull IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(@Nonnull String name,
            @Nonnull Supplier<T> blockSupplier) {
        var block = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
