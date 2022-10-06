package me.jharris.insanityvaults.managers;

import me.jharris.insanityvaults.InsanityVaults;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;

public class VaultManager {

    private InsanityVaults plugin;

    public VaultManager(InsanityVaults plugin) {
        this.plugin = plugin;
    }

    public void createVault(Player player) {
        File file = new File(plugin.getDataFolder(),"/Data/" + player.getUniqueId().toString() + ".yml");
        YamlConfiguration config;

        if(!file.exists()){
            try{
                file.createNewFile();

                config = new YamlConfiguration();
                config.createSection("Items");
                config.save(file);

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public File getVault(Player player) {
        File file = new File(plugin.getDataFolder(),"/Data/" + player.getUniqueId().toString() + ".yml");
        if (!file.exists()) return null;

        return file;
    }

    public void saveVault(Player player, Inventory inventory, File vaultFile) {
        if (vaultFile == null) return;
        YamlConfiguration vaultConfig = YamlConfiguration.loadConfiguration(vaultFile);

        vaultConfig.set("Items", null);
        for (int i=0; i<inventory.getSize(); i++) {
            vaultConfig.set("Items." + i, inventory.getItem(i));
        }

        try {
            vaultConfig.save(vaultFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
