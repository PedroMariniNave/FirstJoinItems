package com.zpedroo.firstjoinitems.utils.config;

import com.zpedroo.firstjoinitems.utils.FileUtils;
import com.zpedroo.firstjoinitems.utils.color.Colorize;

import java.util.List;

public class Messages {

    public static final List<String> COMMAND_USAGE = Colorize.getColored(FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Messages.command-usage"));

    public static final String SUCCESSFUL_PICKUP = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.successful-pickup"));

    public static final String SUCCESSFUL_SEND = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.successful-send"));

    public static final String SUCCESSFUL_SET = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.successful-set"));

    public static final String SUCCESSFUL_RESET = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.successful-reset"));

    public static final String OFFLINE_PLAYER = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.offline-player"));
}