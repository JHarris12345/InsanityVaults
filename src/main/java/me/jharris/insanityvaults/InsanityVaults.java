package me.jharris.insanityvaults;

import me.jharris.insanityvaults.commands.MainCommand;
import me.jharris.insanityvaults.files.MessagesFile;
import me.jharris.insanityvaults.managers.MessagesManager;
import me.jharris.insanityvaults.managers.VaultManager;
import me.jharris.insanityvaults.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class InsanityVaults extends JavaPlugin {

    private InsanityVaults plugin;
    private Utils utils;
    private MessagesManager messagesManager;
    private VaultManager vaultManager;

    @Override
    public void onEnable() {
        plugin = this;
        utils = new Utils();
        messagesManager = new MessagesManager(this);
        vaultManager = new VaultManager(this);

        // Create the config file
        saveDefaultConfig();

        // Create the data folder
        createFolder("Data");

        // Register commands
        getCommand("vault").setExecutor(new MainCommand(this));

        // Startup finished message
        getLogger().info(utils.translateColourCodes("&f---------------------------------------"));
        getLogger().info(utils.translateColourCodes("&fEnabled InsanityVaults"));
        getLogger().info(utils.translateColourCodes("&f---------------------------------------"));
    }

    @Override
    public void onDisable() {
        // Disabled finished message
        getLogger().info(utils.translateColourCodes("&f---------------------------------------"));
        getLogger().info(utils.translateColourCodes("&fDisabled InsanityVaults"));
        getLogger().info(utils.translateColourCodes("&f---------------------------------------"));
    }


    public InsanityVaults getPlugin() {
        return plugin;
    }

    public Utils getUtils() {
        return utils;
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public VaultManager getVaultManager() {
        return vaultManager;
    }

    public void createFolder(String child) {
        File dataFolder = new File(getDataFolder(), child);
        if(!dataFolder.exists()) dataFolder.mkdirs();
    }
}
