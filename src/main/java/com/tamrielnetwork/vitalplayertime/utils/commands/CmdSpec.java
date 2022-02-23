/*
 * VitalPlayerTime is a Spigot Plugin that gives players the ability to change their time.
 * Copyright © 2022 Leopold Meinel
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
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalPlayerTime/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalplayertime.utils.commands;

import com.tamrielnetwork.vitalplayertime.utils.Chat;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdSpec {

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {

		if (Cmd.isInvalidSender(sender)) {
			return true;
		}
		return Cmd.isNotPermitted(sender, perm);
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String arg, @NotNull String perm) {

		if (Cmd.isInvalidSender(sender)) {
			return true;
		}
		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		return isInvalidTime(sender, getTicks(arg));
	}

	public static Long getTicks(@NotNull String arg) {

		switch (arg) {
			case "day" -> {
				return 0L;
			}
			case "morning" -> {
				return 1000L;
			}
			case "noon" -> {
				return 6000L;
			}
			case "afternoon" -> {
				return 9000L;
			}
			case "sunset" -> {
				return 12000L;
			}
			case "night" -> {
				return 14000L;
			}
			case "midnight" -> {
				return 18000L;
			}
			case "sunrise" -> {
				return 23000L;
			}
			default -> {
				return null;
			}
		}
	}

	public static List<String> getNames() {

		return new ArrayList<>(Arrays.asList("day", "morning", "noon", "afternoon", "sunset", "night", "midnight", "sunrise"));
	}

	private static boolean isInvalidTime(@NotNull CommandSender sender, Long getTicks) {

		if (getTicks == null) {
			Chat.sendMessage(sender, "invalid-time");
			return true;
		}
		return false;

	}

}
