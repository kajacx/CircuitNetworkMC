package cz.kajacx.circuitnetworkmc.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import cz.kajacx.circuitnetworkmc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabs {
    @SuppressWarnings("null")
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CircuitNetworkMC.MOD_ID);

    public static final String CREATIVE_TAB_NAME = "circuit_network_tab";
    @SuppressWarnings("null")
    public static final Supplier<CreativeModeTab> CIRCUIT_NETWORK_TAB = CREATIVE_TABS.register(CREATIVE_TAB_NAME,
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CIRCUIT_WIRES.get()))
                    .title(Component.translatable("creativetab.circuitnetworkmc.circuit_network"))
                    .displayItems(CreativeTabs::displayItems)
                    .build());

    @SuppressWarnings("null")
    private static void displayItems(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(ModItems.CIRCUIT_WIRES.get());
        output.accept(ModBlocks.CONSTANT_COMBINATOR_BLOCK);
    }

    public static void register(@Nonnull IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
