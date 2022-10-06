package me.jharris.insanityvaults.managers;

import me.jharris.insanityvaults.InsanityVaults;
import me.jharris.insanityvaults.files.MessagesFile;

import java.io.File;
import java.util.HashMap;

public class MessagesManager {

    private InsanityVaults plugin;
    private HashMap<String, String> messages;
    private MessagesFile messagesFile;

    public MessagesManager(InsanityVaults plugin) {
        this.plugin = plugin;
        this.messagesFile = new MessagesFile(this.plugin);
        this.messages = new HashMap<>();

        for (String key : messagesFile.getCustomFile().getKeys(false)) {
            String message = messagesFile.getCustomFile().getString(key);
            this.messages.put(key, this.plugin.getUtils().translateColourCodes(message.replaceAll("%prefix%", messages.get("Prefix"))));
        }
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }
}
