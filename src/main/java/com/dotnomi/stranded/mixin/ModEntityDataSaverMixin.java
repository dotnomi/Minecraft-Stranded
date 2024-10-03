package com.dotnomi.stranded.mixin;

import com.dotnomi.stranded.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void writePersistentData(NbtCompound tag, CallbackInfoReturnable<?> info) {
        if (persistentData != null) {
            tag.put("stranded.data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readPersistentData(NbtCompound tag, CallbackInfo info) {
        if (tag.contains("stranded.data", 10)) {
            persistentData = tag.getCompound("stranded.data");
        }
    }
}
