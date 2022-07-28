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

package com.tamrielnetwork.vitalplayertime;

import com.tamrielnetwork.vitalplayertime.commands.VitalPlayerTimeCmd;
import com.tamrielnetwork.vitalplayertime.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VitalPlayerTime
		extends JavaPlugin {

	private Messages messages;

	@Override
	public void onEnable() {
		Objects.requireNonNull(getCommand("ptime"))
		       .setExecutor(new VitalPlayerTimeCmd());
		Objects.requireNonNull(getCommand("ptime"))
		       .setTabCompleter(new VitalPlayerTimeCmd());
		messages = new Messages();
		Bukkit.getLogger()
		      .info("VitalPlayerTime v" + this.getDescription()
		                                      .getVersion() + " enabled");
		Bukkit.getLogger()
		      .info("Copyright (C) 2022 Leopold Meinel");
		Bukkit.getLogger()
		      .info("This program comes with ABSOLUTELY NO WARRANTY!");
		Bukkit.getLogger()
		      .info("This is free software, and you are welcome to redistribute it under certain conditions.");
		Bukkit.getLogger()
		      .info("See https://github.com/LeoMeinel/VitalPlayerTime/blob/main/LICENSE for more details.");
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger()
		      .info("VitalPlayerTime v" + this.getDescription()
		                                      .getVersion() + " disabled");
	}

	public Messages getMessages() {
		return messages;
	}
}

