package com.norcode.bukkit.mcadmintools;

import com.norcode.bukkit.mcadmintools.commands.BroadcastCommand;
import com.norcode.bukkit.mcadmintools.commands.CookCommand;
import com.norcode.bukkit.mcadmintools.commands.DispelCommand;
import com.norcode.bukkit.mcadmintools.commands.EnchantCommand;
import com.norcode.bukkit.mcadmintools.commands.FeedCommand;
import com.norcode.bukkit.mcadmintools.commands.FlyCommand;
import com.norcode.bukkit.mcadmintools.commands.FlySpeedCommand;
import com.norcode.bukkit.mcadmintools.commands.GamemodeCommand;
import com.norcode.bukkit.mcadmintools.commands.HealCommand;
import com.norcode.bukkit.mcadmintools.commands.JumpCommand;
import com.norcode.bukkit.mcadmintools.commands.KillCommand;
import com.norcode.bukkit.mcadmintools.commands.MCATCommand;
import com.norcode.bukkit.mcadmintools.commands.MoreCommand;
import com.norcode.bukkit.mcadmintools.commands.PlayerHeadCommand;
import com.norcode.bukkit.mcadmintools.commands.RepairCommand;
import com.norcode.bukkit.mcadmintools.commands.ServerCommand;
import com.norcode.bukkit.mcadmintools.commands.SetSpawnCommand;
import com.norcode.bukkit.mcadmintools.commands.SmiteCommand;
import com.norcode.bukkit.mcadmintools.commands.SpawnCommand;
import com.norcode.bukkit.mcadmintools.commands.SplashCommand;
import com.norcode.bukkit.mcadmintools.commands.SudoCommand;
import com.norcode.bukkit.mcadmintools.commands.SuicideCommand;
import com.norcode.bukkit.mcadmintools.commands.ViewEnderchestCommand;
import com.norcode.bukkit.mcadmintools.commands.ViewInventoryCommand;
import com.norcode.bukkit.mcadmintools.commands.WorkbenchCommand;
import com.norcode.bukkit.mcadmintools.commands.XPCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;


public class MCAdminTools extends JavaPlugin {
	ConfigAccessor messages;
	
	@Override
	public void onLoad() {
		super.onLoad();
		messages = new ConfigAccessor(this, "messages.yml");
	}
	
	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		reloadConfig();
		saveConfig();
		messages.getConfig().options().copyDefaults(true);
		messages.saveDefaultConfig();
		messages.reloadConfig();
        if (getServer().getPluginManager().getPlugin("Votifier") != null) {
            getServer().getPluginManager().registerEvents(new VotifierListener(this), this);
        }
        getServer().getPluginCommand("mcadmintools").setExecutor(new MCATCommand(this));
		getServer().getPluginCommand("heal").setExecutor(new HealCommand(this));
		getServer().getPluginCommand("feed").setExecutor(new FeedCommand(this));
		getServer().getPluginCommand("kill").setExecutor(new KillCommand(this));
		getServer().getPluginCommand("dispel").setExecutor(new DispelCommand(this));
		getServer().getPluginCommand("suicide").setExecutor(new SuicideCommand(this));
		getServer().getPluginCommand("invsee").setExecutor(new ViewInventoryCommand(this));
		getServer().getPluginCommand("enderchest").setExecutor(new ViewEnderchestCommand(this));
		getServer().getPluginCommand("xp").setExecutor(new XPCommand(this));
		getServer().getPluginCommand("workbench").setExecutor(new WorkbenchCommand(this));
		getServer().getPluginCommand("splash").setExecutor(new SplashCommand(this));
		getServer().getPluginCommand("smite").setExecutor(new SmiteCommand(this));
		getServer().getPluginCommand("gamemode").setExecutor(new GamemodeCommand(this));
		getServer().getPluginCommand("repair").setExecutor(new RepairCommand(this));
		getServer().getPluginCommand("jump").setExecutor(new JumpCommand(this));
		getServer().getPluginCommand("more").setExecutor(new MoreCommand(this));
		getServer().getPluginCommand("cook").setExecutor(new CookCommand(this));
		getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getServer().getPluginCommand("spawn").setExecutor(new SpawnCommand(this));
		getServer().getPluginCommand("enchant").setExecutor(new EnchantCommand(this));
        getServer().getPluginCommand("fly").setExecutor(new FlyCommand(this));
        getServer().getPluginCommand("flyspeed").setExecutor(new FlySpeedCommand(this));
        getServer().getPluginCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getServer().getPluginCommand("playerhead").setExecutor(new PlayerHeadCommand(this));
		getServer().getPluginCommand("sudo").setExecutor(new SudoCommand(this));
		getServer().getPluginCommand("server").setExecutor(new ServerCommand(this));
	}

	public String getMsg(String key, Object... args) {
		String tpl = messages.getConfig().getString(key);
		if (tpl == null) {
			tpl = "[" + key + "] ";
			for (int i=0;i<args.length;i++) {
				tpl += "{"+i+"}, ";
			}
		}
		
		return MessageFormat.format(tpl,args);
	}

}
