package me.burzakrual.customitems;

import java.util.ArrayList;
import java.util.List;

import WandVariations.IceWand;
import WandVariations.LightningWand;
import WandVariations.TeleportWand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static double version = 1.3D;

    public static boolean costEnabled = true;

    public static List<Wand> wandVariations = new ArrayList<>();

    public static FileConfiguration config;

    public void onEnable() {
        loadConfig();
        initiateWands();
    }

    void loadConfig() {
        config = getConfig();
        config.addDefault("Wands.Teleport.Cooldown", Integer.valueOf(10));
        config.addDefault("Wands.Ice.Cooldown", Integer.valueOf(20));
        config.addDefault("Wands.Lightning.Cooldown", Integer.valueOf(60));

        config.options().copyDefaults(true);
        saveConfig();
    }

    void initiateWands() {
        wandVariations.add(new TeleportWand(this, ChatColor.AQUA + "Teleport Wand", ChatColor.AQUA + "uncommon", config.getInt("Wands.Teleport.Cooldown")));
        wandVariations.add(new IceWand(this, ChatColor.BLUE + "[Excalibur] ", ChatColor.BLUE + "Freeze your enemy for 2s", config.getInt("Wands.Ice.Cooldown")));
        wandVariations.add(new LightningWand(this, ChatColor.LIGHT_PURPLE + "Lightning Wand", ChatColor.LIGHT_PURPLE + "YOU ARE A THUNDER GOD", config.getInt("Wands.Lightning.Cooldown")));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandHandler.handleCommand(sender, cmd, label, args);
        return true;
    }
}
