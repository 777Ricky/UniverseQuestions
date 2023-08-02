package me.rickylafleur.questions.listeners;

import me.rickylafleur.questions.QuestionsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {
    private final QuestionsPlugin plugin;

    public PlayerListener(QuestionsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("questions.bypass")) {
            return;
        }

        String message = event.getMessage();

        Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.getQuestionManager().getQuestions().stream()
                .filter(questionData -> plugin.containsWords(message, questionData.getIdentifier().split("\\|")))
                .forEach(questionData -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', questionData.getResponse()))), 10);
    }
}
