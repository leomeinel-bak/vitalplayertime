/*
 * File: CmdSpec.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalplayertime.utils.commands;

import dev.meinel.leo.vitalplayertime.utils.Chat;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdSpec {

    private CmdSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {
        return Cmd.isInvalidSender(sender) || !Cmd.isPermitted(sender, perm);
    }

    public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String arg,
            @NotNull String perm) {
        return Cmd.isInvalidSender(sender) || !Cmd.isPermitted(sender, perm)
                || isInvalidTime(sender, getTicks(arg));
    }

    public static Long getTicks(@NotNull String arg) {
        return switch (arg) {
            case "day" -> 0L;
            case "morning" -> 1000L;
            case "noon" -> 6000L;
            case "afternoon" -> 9000L;
            case "sunset" -> 12000L;
            case "night" -> 14000L;
            case "midnight" -> 18000L;
            case "sunrise" -> 23000L;
            default -> null;
        };
    }

    public static List<String> getNames() {
        return new ArrayList<>(Arrays.asList("day", "morning", "noon", "afternoon", "sunset",
                "night", "midnight", "sunrise"));
    }

    private static boolean isInvalidTime(@NotNull CommandSender sender, Long getTicks) {
        if (getTicks == null) {
            Chat.sendMessage(sender, "invalid-time");
            return true;
        }
        return false;
    }
}
