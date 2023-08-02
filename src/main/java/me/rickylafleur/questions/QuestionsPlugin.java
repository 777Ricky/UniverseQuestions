package me.rickylafleur.questions;

import me.rickylafleur.questions.data.QuestionManager;
import me.rickylafleur.questions.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class QuestionsPlugin extends JavaPlugin {
    private static QuestionsPlugin plugin;

    private final QuestionManager questionManager = new QuestionManager(this);

    @Override
    public void onEnable() {
        plugin = this;

        questionManager.load();
        reload();
        registerEvents();
    }

    private void reload() {
        saveDefaultConfig();
        reloadConfig();
    }

    public static QuestionsPlugin getInstance() {
        return plugin;
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(this), this);
    }
}
