package me.rickylafleur.questions.listeners;

import me.rickylafleur.questions.QuestionsPlugin;
import me.rickylafleur.questions.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.List;

public class PlayerListener implements Listener {
    private final QuestionsPlugin plugin;

    public PlayerListener(QuestionsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (player.hasPermission("questions.bypass")) {
            return;
        }

        final String message = event.getMessage();

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> plugin.getQuestionManager().getQuestions().stream()
                .filter(questionData -> Utils.containsWords(message, questionData.getIdentifier().split("\\|")))
                .forEach(questionData -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', questionData.getResponse()))), 10);
    }


}
