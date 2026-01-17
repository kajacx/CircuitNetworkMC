package cz.kajacx.circuitnetworkmc.client;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import cz.kajacx.circuitnetworkmc.item.ModItems;
import cz.kajacx.circuitnetworkmc.item.circuitwires.CircuitWiresItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

@EventBusSubscriber(modid = CircuitNetworkMC.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemModelProperties {
    @SubscribeEvent
    public static void registerItemProperties(RegisterItemDecorationsEvent event) {
        ItemProperties.register(ModItems.CIRCUIT_WIRES.get(),
                ResourceLocation.fromNamespaceAndPath(CircuitNetworkMC.MOD_ID, "wire_color"),
                (itemStack, clientLevel, livingEntity, seed) -> CircuitWiresItem.getColor(itemStack));
    }
}
