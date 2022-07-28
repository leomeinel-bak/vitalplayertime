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

package com.tamrielnetwork.vitalplayertime.commands;

import com.tamrielnetwork.vitalplayertime.utils.Chat;
import com.tamrielnetwork.vitalplayertime.utils.commands.Cmd;
import com.tamrielnetwork.vitalplayertime.utils.commands.CmdSpec;
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