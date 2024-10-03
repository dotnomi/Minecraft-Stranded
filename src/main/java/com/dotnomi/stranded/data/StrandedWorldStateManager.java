package com.dotnomi.stranded.data;

import com.dotnomi.stranded.Stranded;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentStateManager;

public class StrandedWorldStateManager {
    public static StrandedWorldState getStrandedWorldState(ServerWorld world) {
        PersistentStateManager persistentStateManager = world.getPersistentStateManager();
        StrandedWorldState strandedWorldState = persistentStateManager.getOrCreate(StrandedWorldState.TYPE, Stranded.MOD_ID);
        strandedWorldState.markDirty();
        return strandedWorldState;
    }
}
