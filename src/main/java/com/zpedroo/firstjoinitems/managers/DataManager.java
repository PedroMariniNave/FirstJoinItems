package com.zpedroo.firstjoinitems.managers;

import com.zpedroo.firstjoinitems.managers.cache.DataCache;
import com.zpedroo.firstjoinitems.objects.DefaultInventory;
import com.zpedroo.firstjoinitems.utils.FileUtils;
import com.zpedroo.firstjoinitems.utils.encoder.Base64Encoder;
import org.bukkit.configuration.file.FileConfiguration;

public class DataManager {

    private static DataManager instance;
    public static DataManager getInstance() { return instance; }

    private final DataCache dataCache = new DataCache();

    public DataManager() {
        instance = this;
    }

    public DefaultInventory getDefaultInventory() {
        return dataCache.getDefaultInventory();
    }

    public void setDefaultInventory(DefaultInventory defaultInventory) {
        dataCache.setDefaultInventory(defaultInventory);
    }

    public void saveDefaultInventoryInFile() {
        DefaultInventory defaultInventory = dataCache.getDefaultInventory();
        if (defaultInventory == null) return;

        String serializedInventory = Base64Encoder.itemStackArrayToBase64(defaultInventory.getInventoryItems());
        String serializedArmor = Base64Encoder.itemStackArrayToBase64(defaultInventory.getArmorItems());

        FileUtils.Files file = FileUtils.Files.ITEMS;
        FileUtils.FileManager fileManager = FileUtils.get().getFile(file);
        FileConfiguration fileConfiguration = fileManager.get();

        fileConfiguration.set("Items.inventory", serializedInventory);
        fileConfiguration.set("Items.armor", serializedArmor);
        fileManager.save();
    }

    public DataCache getCache() {
        return dataCache;
    }
}