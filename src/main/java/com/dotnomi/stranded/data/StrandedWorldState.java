package com.dotnomi.stranded.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.List;

public class StrandedWorldState extends PersistentState {
    private static List<String> unlockedVoiceovers = new ArrayList<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList nbtUnlockedVoiceovers = new NbtList();
        unlockedVoiceovers.forEach(voiceover -> nbtUnlockedVoiceovers.add(NbtString.of(voiceover)));
        nbt.put("unlocked_voiceovers", nbtUnlockedVoiceovers);
        return nbt;
    }

    public static final Type<StrandedWorldState> TYPE = new Type<>(
            StrandedWorldState::new,
            StrandedWorldState::fromNbt,
            null
    );

    public static StrandedWorldState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        StrandedWorldState state = new StrandedWorldState();

        unlockedVoiceovers = new ArrayList<>();
        NbtList nbtUnlockedVoiceovers = nbt.getList("unlocked_voiceovers", NbtElement.STRING_TYPE);
        nbtUnlockedVoiceovers.forEach(nbtVoiceover -> unlockedVoiceovers.add((nbtVoiceover).asString()));

        return state;
    }

    public void addUnlockedVoiceover(String voiceoverId) {
        unlockedVoiceovers.add(voiceoverId);
        markDirty();
    }

    public void removeDummyData(String voiceoverId) {
        unlockedVoiceovers.remove(voiceoverId);
        markDirty();
    }

    public List<String> getUnlockedVoiceovers() {
        return unlockedVoiceovers;
    }

    public boolean isVoiceoverUnlocked(String voiceoverId) {
        return unlockedVoiceovers.contains(voiceoverId);
    }
}
