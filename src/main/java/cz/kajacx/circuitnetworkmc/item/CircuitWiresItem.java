package cz.kajacx.circuitnetworkmc.item;

import javax.annotation.Nonnull;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class CircuitWiresItem extends Item {
    public CircuitWiresItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(@Nonnull UseOnContext context) {
        CircuitNetworkMC.LOGGER.info("Circuit Wires Item used on block at " + context.getClickedPos());

        return InteractionResult.PASS;
    }
}
