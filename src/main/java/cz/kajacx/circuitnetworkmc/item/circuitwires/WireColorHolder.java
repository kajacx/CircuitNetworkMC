package cz.kajacx.circuitnetworkmc.item.circuitwires;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.neoforged.neoforge.common.MutableDataComponentHolder;

public class WireColorHolder implements MutableDataComponentHolder {
    private int color;
    private final PatchedDataComponentMap components;

    @SuppressWarnings("null")
    public WireColorHolder(int initialColor) {
        this.color = initialColor;
        this.components = new PatchedDataComponentMap(DataComponentMap.EMPTY);
    }

    @Override
    public DataComponentMap getComponents() {
        return this.components;
    }

    @Override
    public <T> @Nullable T set(DataComponentType<? super T> componentType, @Nullable T value) {
        return this.components.set(componentType, value);
    }

    @Override
    public <T> @Nullable T remove(DataComponentType<? extends T> componentType) {
        return this.components.remove(componentType);
    }

    @Override
    public void applyComponents(DataComponentPatch patch) {
        this.components.applyPatch(patch);
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        this.components.setAll(components);
    }

}
