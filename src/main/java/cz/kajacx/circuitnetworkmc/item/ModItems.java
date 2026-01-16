package cz.kajacx.circuitnetworkmc.item;

import javax.annotation.Nonnull;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CircuitNetworkMC.MOD_ID);

    public static final String CIRCUIT_WIRES_ITEM_NAME = "circuit_wires";
    @SuppressWarnings("null")
    public static final @Nonnull DeferredItem<Item> CIRCUIT_WIRES = ITEMS.register(CIRCUIT_WIRES_ITEM_NAME,
            () -> new Item(new Item.Properties()));

    public static void register(@Nonnull IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
