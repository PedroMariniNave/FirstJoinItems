package com.zpedroo.firstjoinitems.managers.cache;

import com.zpedroo.firstjoinitems.objects.DefaultInventory;
import com.zpedroo.firstjoinitems.utils.FileUtils;
import com.zpedroo.firstjoinitems.utils.encoder.Base64Encoder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class DataCache {

    private DefaultInventory defaultInventory = getDefaultInventoryFromFile();

    private DefaultInventory getDefaultInventoryFromFile() {
        FileUtils.Files file = FileUtils.Files.ITEMS;
        String serializedInventory = FileUtils.get().getString(file, "Items.inventory");
        String serializedArmor = FileUtils.get().getString(file, "Items.armor");

        ItemStack[] inventoryItems = Base64Encoder.itemStackArrayFromBase64(serializedInventory);
        ItemStack[] armorItems = Base64Encoder.itemStackArrayFromBase64(serializedArmor);

        return new DefaultInventory(inventoryItems, armorItems);
    }
}