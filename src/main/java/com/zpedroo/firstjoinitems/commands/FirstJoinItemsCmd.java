package com.zpedroo.firstjoinitems.commands;

import com.zpedroo.firstjoinitems.managers.DataManager;
import com.zpedroo.firstjoinitems.objects.DefaultInventory;
import com.zpedroo.firstjoinitems.utils.config.Messages;
import com.zpedroo.firstjoinitems.utils.inventory.DefaultInventoryUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FirstJoinItemsCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        if (args.length > 0) {
            DefaultInventory defaultInventory = null;
            switch (args[0].toUpperCase()) {
                case "SET":
                    ItemStack[] items = player.getInventory().getContents();
                    ItemStack[] armor = player.getInventory().getArmorContents();
                    defaultInventory = new DefaultInventory(items, armor);
                    DataManager.getInstance().setDefaultInventory(defaultInventory);

                    clearInventory(player);
                    player.sendMessage(Messages.SUCCESSFUL_SET);
                    return true;
                case "PICKUP":
                    DefaultInventoryUtils.giveItemsToPlayer(player);
                    player.sendMessage(Messages.SUCCESSFUL_PICKUP);
                    return true;
                case "SEND":
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(Messages.OFFLINE_PLAYER);
                        return true;
                    }

                    DefaultInventoryUtils.giveItemsToPlayer(target);
                    player.sendMessage(StringUtils.replaceEach(Messages.SUCCESSFUL_SEND, new String[]{
                            "{player}"
                    }, new String[]{
                            target.getName()
                    }));
                    return true;

                case "RESET":
                    DataManager.getInstance().setDefaultInventory(null);
                    player.sendMessage(Messages.SUCCESSFUL_RESET);
                    return true;
            }
        }

        for (String message : Messages.COMMAND_USAGE) {
            sender.sendMessage(message);
        }
        return false;
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
    }
}