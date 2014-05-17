package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class SudoCommand extends BaseCommand {

	public SudoCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
		 return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
		if ((sender instanceof ConsoleCommandSender) || sender.hasPermission("mcadmintools.sudo")) {
			if (args.size() < 2) {
				sender.sendMessage("Usage: /" + alias + " <playerName> <command> [<command arg(s)> ...]");
				return true;
			}
			Player player = plugin.getServer().getPlayerExact(args.peek());
			if  (player == null) {
				sender.sendMessage("Unknown Player: " + args.peek());
				return true;
			}
			args.pop();
			player.performCommand(StringUtils.join(args, ' '));
		}
		return true;
	}
}
