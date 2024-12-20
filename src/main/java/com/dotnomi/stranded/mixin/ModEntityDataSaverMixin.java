package com.dotnomi.stranded.mixin;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
    @Unique
    private static final String FILE_KEY = Stranded.MOD_ID + ".data";

    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound minecraft_Stranded$getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void writePersistentData(NbtCompound tag, CallbackInfoReturnable<?> info) {
        if (persistentData != null) {
            tag.put(FILE_KEY, persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readPersistentData(NbtCompound tag, CallbackInfo info) {
        if (tag.contains( FILE_KEY, 10)) {
            persistentData = tag.getCompound(FILE_KEY);
        }
    }
}
