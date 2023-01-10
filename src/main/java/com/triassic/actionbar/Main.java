package com.triassic.actionbar;

import java.util.Objects;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.clip.placeholderapi.PlaceholderAPI;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        if (this.getConfig().getBoolean("actionbar.enabled")) {
            BukkitScheduler scheduler = this.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(this, this::Actionbar, 0L, 0L);
        }

        this.saveDefaultConfig();
    }

    String actionMessage = Objects.requireNonNull(this.getConfig().getString("actionbar.message"));

    public void Actionbar() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            String message = PlaceholderAPI.setPlaceholders(p,(actionMessage));
            String parsedOutput = ChatColor.translateAlternateColorCodes('&', message);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(parsedOutput));
        }
    }
}

