package com.dotnomi.stranded.service;

import com.dotnomi.stranded.constant.ExampleTags;
import com.dotnomi.stranded.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

@SuppressWarnings("UnusedReturnValue")
public class ExampleDataService {
    public static int addNumber(IEntityDataSaver player, int amount) {
        NbtCompound tag = player.getPersistentData();
        int number = tag.getInt(ExampleTags.NUMBER.getValue()) + amount;
        tag.putInt(ExampleTags.NUMBER.getValue(), number);
        return number;
    }

    public static int removeNumber(IEntityDataSaver player, int amount) {
        NbtCompound tag = player.getPersistentData();
        int number = tag.getInt(ExampleTags.NUMBER.getValue()) - amount;
        tag.putInt(ExampleTags.NUMBER.getValue(), number);
        return number;
    }

    public static int getNumber(IEntityDataSaver player) {
        NbtCompound tag = player.getPersistentData();
        return tag.getInt(ExampleTags.NUMBER.getValue());
    }
}
