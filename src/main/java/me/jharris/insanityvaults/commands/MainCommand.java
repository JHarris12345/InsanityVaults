package me.jharris.insanityvaults.commands;

import me.jharris.insanityvaults.InsanityVaults;
import me.jharris.insanityvaults.inventories.VaultGUI;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class MainCommand extends Command implements TabExecutor {

    InsanityVaults plugin;

    public MainCommand(InsanityVaults plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!super.passedSingleUsageCommandPreChecks(command, args, sender, true,
                plugin.getConfig().getString("Permission"), 0, true)) return true;

        Player player = (Player) sender;

        // Create their vault first (does nothing if already created)
        plugin.getVaultManager().createVault(player);

        // Open the vault
        player.openInventory(new VaultGUI(plugin, player).getInventory());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
