package me.jharris.insanityvaults.commands;

import me.jharris.insanityvaults.InsanityVaults;
import me.jharris.insanityvaults.managers.MessagesManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command {

    private InsanityVaults plugin;

    public Command(InsanityVaults plugin) {
        this.plugin = plugin;
    }

    public boolean passedSingleUsageCommandPreChecks(org.bukkit.command.Command command, String[] commandArgs, CommandSender sender,
                                                     boolean mustBePlayer, String requiredPermission, int args, boolean enforceArgs) {

        if (mustBePlayer && !(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessagesManager().getMessages().get("MustBePlayer"));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(requiredPermission)) {
            sender.sendMessage(plugin.getMessagesManager().getMessages().get("NoPermission"));
            return false;
        }

        if (enforceArgs && commandArgs.length != args) {
            sender.sendMessage(plugin.getMessagesManager().getMessages().get("InvalidCommand")
                    .replace("%usage%", command.getUsage()));
            return false;
        }

        return true;
    }
}
