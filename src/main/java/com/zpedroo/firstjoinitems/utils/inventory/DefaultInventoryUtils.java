package com.zpedroo.firstjoinitems.utils.inventory;

import com.zpedroo.firstjoinitems.managers.DataManager;
import com.zpedroo.firstjoinitems.objects.DefaultInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultInventoryUtils {

    public static void giveItemsToPlayer(Player player) {
        DefaultInventory defaultInventory = DataManager.getInstance().getDefaultInventory();
        if (defaultInventory == null) return;

        ItemStack[] inventoryItems = defaultInventory.getInventoryItems();
        for (ItemStack item : inventoryItems) {
            giveItemToPlayer(player, item);
        }

        ItemStack[] armorItems = defaultInventory.getArmorItems();
        if (!hasArmor(player)) {
            player.getInventory().setArmorContents(armorItems);
        } else {
            for (ItemStack item : armorItems) {
                giveItemToPlayer(player, item);
            }
        }
    }

    private static void giveItemToPlayer(Player player, ItemStack item) {
        if (item == null || item.getType().equals(Material.AIR)) return;

        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(item);
        } else {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }

    private static boolean hasArmor(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) return true;
        }

        return false;
    }
}