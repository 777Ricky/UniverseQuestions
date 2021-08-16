package me.rickylafleur.listeners;

import me.rickylafleur.UniverseQuestions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {
    private final UniverseQuestions plugin;

    public PlayerListener(UniverseQuestions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("unifaq.bypass")) {
            return;
        }

        String message = event.getMessage();

        Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.getQuestionManager().getQuestions().stream()
                .filter(questionData -> plugin.containsWords(message, questionData.getIdentifier().split("\\|")))
                .forEach(questionData -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', questionData.getResponse()))), 10);
    }
}
