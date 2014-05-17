package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpawnCommand extends BaseCommand {

    public SpawnCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
            String alias, LinkedList<String> args) {
        List<String> matches = new ArrayList<>();
        if (args.size() == 1) {
            for (Player p: plugin.getServer().matchPlayer(args.peek())) {
                if ((sender instanceof Player) && ((Player)sender).canSee(p)) {
                    matches.add(p.getName());
                }
            }
        } else if (args.size() == 2) {

			for (World w: plugin.getServer().getWorlds()) {
				if (w.getName().toLowerCase().startsWith(args.peekLast())) {

				}
			}
		}
        return matches;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String alias, LinkedList<String> args) {
        Player target = null;
        if (args.size() == 0) {
            if (!(sender instanceof Player)) {
                return false;
            }
            target = (Player) sender;
        } else {
            if (!sender.hasPermission("mcadmintools.spawn.others")) {
                return false;
            }
            try {
                target = parsePlayer(args.peek());
            } catch (AmbiguousPlayerName ex) {
                sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
            } catch (PlayerNotFound ex) {
                sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
            }
            if (target == null) return true;
        }
        target.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
        return true;
    }

}
