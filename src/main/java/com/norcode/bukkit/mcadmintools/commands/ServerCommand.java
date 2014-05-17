package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ServerCommand extends BaseCommand {
	public ServerCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
		if ((sender instanceof Player) && sender.hasPermission("mcadmintools.server")) {
			byte[] message = buildConnectMessage(args.peek());
			((Player) sender).sendPluginMessage(plugin, "BungeeCord", message);
		}
		return true;
	}

	private byte[] buildConnectMessage(String serverName) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream daos = new DataOutputStream(baos);
		try {
			daos.writeUTF("Connect");
			daos.writeUTF(serverName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
