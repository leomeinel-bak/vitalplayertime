/*
 * VitalPlayerTime is a Spigot Plugin that gives players the ability to change their time.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/LeoMeinel/VitalPlayerTime/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalplayertime.utils.commands;

import com.tamrielnetwork.vitalplayertime.utils.Chat;
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
		return Cmd.isInvalidSender(sender) || Cmd.isNotPermitted(sender, perm);
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String arg, @NotNull String perm) {
		return Cmd.isInvalidSender(sender) || Cmd.isNotPermitted(sender, perm) || isInvalidTime(sender, getTicks(arg));
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
		return new ArrayList<>(
				Arrays.asList("day", "morning", "noon", "afternoon", "sunset", "night", "midnight", "sunrise"));
	}

	private static boolean isInvalidTime(@NotNull CommandSender sender, Long getTicks) {
		if (getTicks == null) {
			Chat.sendMessage(sender, "invalid-time");
			return true;
		}
		return false;
	}
}
