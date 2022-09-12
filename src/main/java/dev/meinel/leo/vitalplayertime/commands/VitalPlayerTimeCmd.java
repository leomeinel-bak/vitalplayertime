/*
 * File: VitalPlayerTimeCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalplayertime.commands;

import dev.meinel.leo.vitalplayertime.utils.Chat;
import dev.meinel.leo.vitalplayertime.utils.commands.Cmd;
import dev.meinel.leo.vitalplayertime.utils.commands.CmdSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class VitalPlayerTimeCmd
        implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (Cmd.isArgsLengthGreaterThan(sender, args, 1)) {
            return false;
        }
        if (args.length == 0) {
            doPlayerTime(sender);
            return true;
        }
        doPlayerTime(sender, args);
        return true;
    }

    private void doPlayerTime(@NotNull CommandSender sender) {
        if (CmdSpec.isInvalidCmd(sender, "vitalplayertime.set")) {
            return;
        }
        Player senderPlayer = (Player) sender;
        senderPlayer.resetPlayerTime();
        Chat.sendMessage(sender, "reset-time");
    }

    private void doPlayerTime(@NotNull CommandSender sender, @NotNull String[] args) {
        if (CmdSpec.isInvalidCmd(sender, args[0].toLowerCase(), "vitalplayertime.set")) {
            return;
        }
        Player senderPlayer = (Player) sender;
        Long time = CmdSpec.getTicks(args[0].toLowerCase());
        assert time != null;
        senderPlayer.setPlayerTime(time, false);
        Chat.sendMessage(sender, Map.of("%time%", args[0].toLowerCase()), "set-time");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return CmdSpec.getNames();
        }
        return null;
    }
}