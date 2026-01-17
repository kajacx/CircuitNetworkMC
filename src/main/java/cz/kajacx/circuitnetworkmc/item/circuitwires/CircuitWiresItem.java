package cz.kajacx.circuitnetworkmc.item.circuitwires;

import javax.annotation.Nonnull;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class CircuitWiresItem extends Item {
    private static final String[] COLORS = { "red", "green" };
    private static final int NUM_COLORS = COLORS.length;

    public CircuitWiresItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(@Nonnull UseOnContext context) {
        CircuitNetworkMC.LOGGER.info("Circuit Wires Item used on block at " + context.getClickedPos());

        return InteractionResult.PASS;
    }

    public static int getColor(ItemStack itemStack) {
        @SuppressWarnings("null")
        var wiresData = itemStack.get(CircuitWiresData.BASIC_EXAMPLE.get());
        return wiresData == null ? 0 : wiresData.color();
    }

    @SuppressWarnings("null")
    public static void setColor(ItemStack itemStack, int color) {
        itemStack.set(CircuitWiresData.BASIC_EXAMPLE.get(), new CircuitWiresData(fixColor(color)));
    }

    public static int fixColor(int color) {
        return (color % NUM_COLORS + NUM_COLORS) % NUM_COLORS;
    }

    public static void cycleColor(ItemStack itemStack, int scrollAmount) {
        int currentColor = getColor(itemStack);
        int newColor = fixColor(currentColor + scrollAmount);
        CircuitNetworkMC.LOGGER.info("Changing wire color from {} to {}", COLORS[currentColor], COLORS[newColor]);
        setColor(itemStack, newColor);
    }

    @Override
    public Component getName(@Nonnull ItemStack itemStack) {
        int color = getColor(itemStack);
        return Component.literal("Circuit wires (" + COLORS[color] + ")");
    }
}
