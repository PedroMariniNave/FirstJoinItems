package com.zpedroo.firstjoinitems;

import com.zpedroo.firstjoinitems.commands.FirstJoinItemsCmd;
import com.zpedroo.firstjoinitems.listeners.PlayerGeneralListeners;
import com.zpedroo.firstjoinitems.managers.DataManager;
import com.zpedroo.firstjoinitems.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import static com.zpedroo.firstjoinitems.utils.config.Settings.*;

public class FirstJoinItems extends JavaPlugin {

    public void onEnable() {
        new FileUtils(this);
        new DataManager();

        registerListeners();
        registerCommand(COMMAND, ALIASES, PERMISSION, PERMISSION_MESSAGE, new FirstJoinItemsCmd());
    }

    public void onDisable() {
        DataManager.getInstance().saveDefaultInventoryInFile();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerGeneralListeners(), this);
    }

    private void registerCommand(String command, List<String> aliases, String permission, String permissionMessage, CommandExecutor commandExecutor) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            PluginCommand pluginCmd = constructor.newInstance(command, this);
            pluginCmd.setAliases(aliases);
            pluginCmd.setExecutor(commandExecutor);
            if (permission != null && !permission.isEmpty()) pluginCmd.setPermission(permission);
            if (permissionMessage != null && !permissionMessage.isEmpty()) pluginCmd.setPermission(permissionMessage);

            Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            commandMap.register(getName().toLowerCase(), pluginCmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}