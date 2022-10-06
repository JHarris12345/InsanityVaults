package me.jharris.insanityvaults.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public String translateColourCodes(String string) {
        Pattern pattern = Pattern.compile("#[A-Fa-f0-9]{6}");
        Matcher matcher = pattern.matcher(string);
        String output = ChatColor.translateAlternateColorCodes('&', string);

        while (matcher.find()) {
            String color = string.substring(matcher.start(), matcher.end());
            output = output.replace(color, "" + net.md_5.bungee.api.ChatColor.of(color));
        }

        return output;
    }
}
