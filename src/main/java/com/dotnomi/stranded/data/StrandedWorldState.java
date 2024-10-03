package com.dotnomi.stranded.data;

import com.dotnomi.stranded.Stranded;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

public class StrandedWorldState extends PersistentState {
    private int dummyData;

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt(Stranded.MOD_ID + "dummy_data", dummyData);
        return nbt;
    }

    public static final Type<StrandedWorldState> TYPE = new Type<>(
            StrandedWorldState::new,
            StrandedWorldState::fromNbt,
            null
    );

    public static StrandedWorldState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        StrandedWorldState state = new StrandedWorldState();
        state.dummyData = nbt.getInt(Stranded.MOD_ID + "dummy_data");
        return state;
    }

    public void addDummyData(int amount) {
        dummyData += amount;
        markDirty();
    }

    public void removeDummyData(int amount) {
        dummyData -= amount;
        markDirty();
    }

    public void setDummyData(int dummyData) {
        this.dummyData = dummyData;
        markDirty();
    }

    public int getDummyData() {
        return dummyData;
    }
}
