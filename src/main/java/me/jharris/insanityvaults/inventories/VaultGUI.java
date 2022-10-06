package me.jharris.insanityvaults.inventories;

import me.jharris.insanityvaults.InsanityVaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class VaultGUI implements InventoryHolder, Listener {
    private InsanityVaults plugin;
    private Inventory inv;
    private Player player;
    private File vaultFile;

    public VaultGUI(InsanityVaults plugin, Player player) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.player = player;
        this.plugin = plugin;
        this.vaultFile = plugin.getVaultManager().getVault(player);
        this.inv = Bukkit.createInventory(this, plugin.getConfig().getInt("Inventory.Slots"),
                plugin.getUtils().translateColourCodes(plugin.getConfig().getString("Inventory.Title").replace("%name%", player.getName())));

        this.addContents();
    }

    private void addContents() {
        if (vaultFile == null) return;
        YamlConfiguration vaultConfig = YamlConfiguration.loadConfiguration(vaultFile);

        for (String slot : vaultConfig.getConfigurationSection("Items").getKeys(false)) {
            ItemStack item = vaultConfig.getItemStack("Items." + slot);

            inv.setItem(Integer.valueOf(slot), item);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() != this) return;

        HandlerList.unregisterAll(this);
        plugin.getVaultManager().saveVault(player, inv, vaultFile);
    }




    // LISTENERS
    @EventHandler
    public void onBlacklistedItem(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (e.getWhoClicked().getOpenInventory().getTopInventory().getHolder() != this) return;

        if (e.getClick() == ClickType.NUMBER_KEY) {
            e.setCancelled(true);
            return;
        }

        ItemStack itemStack = e.getCurrentItem();
        if (!plugin.getConfig().getStringList("BlacklistedItems").contains(itemStack.getType().toString())) return;

        e.setCancelled(true);
        e.getWhoClicked().sendMessage(plugin.getMessagesManager().getMessages().get("BadItem"));
    }
}
