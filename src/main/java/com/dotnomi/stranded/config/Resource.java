package com.dotnomi.stranded.config;

import com.dotnomi.stranded.Stranded;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class Resource {
    private int id = 0;
    private String item = "minecraft:air";
    private int amount = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        Identifier itemId = Identifier.of(item);
        if (!Registries.ITEM.containsId(itemId)) {
            Stranded.LOGGER.warn("Item {} not found", item);
        }
        return Registries.ITEM.get(itemId);
    }

    public void setItem(Item item) {
        this.item = item.toString();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
