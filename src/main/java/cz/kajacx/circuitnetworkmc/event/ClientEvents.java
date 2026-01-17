package cz.kajacx.circuitnetworkmc.event;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import cz.kajacx.circuitnetworkmc.item.circuitwires.CircuitWiresItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = CircuitNetworkMC.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        var player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem() instanceof CircuitWiresItem)) {
            return;
        }

        if (!Screen.hasShiftDown()) {
            return;
        }

        CircuitNetworkMC.LOGGER.debug("Mouse scrolled: {}, {}", event.getScrollDeltaX(), event.getScrollDeltaY());

        int scrolled = (int) event.getScrollDeltaY();
        if (scrolled != 0) {
            CircuitWiresItem.cycleColor(itemStack, scrolled);
            event.setCanceled(true);
        }
    }
}
