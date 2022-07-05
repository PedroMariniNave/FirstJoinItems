package com.zpedroo.firstjoinitems.listeners;

import com.zpedroo.firstjoinitems.utils.inventory.DefaultInventoryUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerGeneralListeners implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPlayedBefore()) return;

        Player player = event.getPlayer();
        DefaultInventoryUtils.giveItemsToPlayer(player);
    }
}