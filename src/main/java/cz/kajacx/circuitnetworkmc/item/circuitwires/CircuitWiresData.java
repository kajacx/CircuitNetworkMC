package cz.kajacx.circuitnetworkmc.item.circuitwires;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import cz.kajacx.circuitnetworkmc.CircuitNetworkMC;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public record CircuitWiresData(int color) {
    // Using CircuitWiresData(int, boolean)
    // Only one Codec and/or StreamCodec should be used below
    // Multiple are provided for an example

    // Basic codec
    @SuppressWarnings("null")
    public static final @Nonnull Codec<CircuitWiresData> BASIC_CODEC = RecordCodecBuilder.create(instance -> instance
            .group(Codec.INT.fieldOf("color").forGetter(CircuitWiresData::color))
            .apply(instance, CircuitWiresData::new));

    @SuppressWarnings("null")
    public static final @Nonnull StreamCodec<ByteBuf, CircuitWiresData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, CircuitWiresData::color,
            CircuitWiresData::new);

    // Unit stream codec if nothing should be sent across the network
    // public static final StreamCodec<ByteBuf, CircuitWiresData> UNIT_STREAM_CODEC
    // = StreamCodec
    // .unit(new CircuitWiresData(0));

    // In another class
    // The specialized DeferredRegister.DataComponents simplifies data component
    // registration and avoids some generic inference issues with the
    // `DataComponentType.Builder` within a `Supplier`
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister
            .createDataComponents(CircuitNetworkMC.MOD_ID);

    public static final String CIRCUIT_WIRES_COMPONENT_NAME = "circuit_wires_data";
    public static final Supplier<DataComponentType<CircuitWiresData>> BASIC_EXAMPLE = REGISTRAR.registerComponentType(
            CIRCUIT_WIRES_COMPONENT_NAME,
            builder -> builder
                    // The codec to read/write the data to disk
                    .persistent(BASIC_CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(BASIC_STREAM_CODEC));

    // /// Component will not be saved to disk
    // public static final Supplier<DataComponentType<CircuitWiresData>>
    // TRANSIENT_EXAMPLE = REGISTRAR
    // .registerComponentType(
    // "transient",
    // builder -> builder.networkSynchronized(BASIC_STREAM_CODEC));

    // // No data will be synced across the network
    // public static final Supplier<DataComponentType<CircuitWiresData>>
    // NO_NETWORK_EXAMPLE = REGISTRAR
    // .registerComponentType(
    // "no_network",
    // builder -> builder
    // .persistent(BASIC_CODEC)
    // // Note we use a unit stream codec here
    // .networkSynchronized(UNIT_STREAM_CODEC));

    public static void register(@Nonnull IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}