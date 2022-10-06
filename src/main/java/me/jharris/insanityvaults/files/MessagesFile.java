package me.jharris.insanityvaults.files;

import me.jharris.insanityvaults.InsanityVaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;

public class MessagesFile {

    private InsanityVaults plugin;
    private File file;
    private YamlConfiguration customFile;

    public MessagesFile(InsanityVaults plugin) {
        this.plugin = plugin;

        file = new File(plugin.getDataFolder(),"Messages.yml");

        if(!file.exists()){
            try{
                file.createNewFile();

                customFile = new YamlConfiguration();
                customFile.set("Prefix", "&7[&b◎&7]");
                customFile.set("NoPermission", "%prefix% You don't have permission to do this");
                customFile.set("BadItem", "%prefix% You can't put this item in your vault");
                customFile.set("MustBePlayer", "%prefix% You must be a player to execute this command");
                customFile.set("InvalidCommand", "%prefix% Invalid command. Did you mean &b%usage%");

                customFile.save(file);

            } catch(IOException e){
                e.printStackTrace();
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getCustomFile() {
        return customFile;
    }
}
