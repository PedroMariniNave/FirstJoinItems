package com.zpedroo.firstjoinitems.objects;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public class DefaultInventory {

    private final ItemStack[] inventoryItems;
    private final ItemStack[] armorItems;
}