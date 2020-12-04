package me.rickylafleur;

import me.rickylafleur.data.QuestionManager;
import me.rickylafleur.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class UniverseQuestions extends JavaPlugin {
    private static UniverseQuestions plugin;

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

    public static UniverseQuestions getInstance() {
        return plugin;
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(this), this);
    }

    public boolean containsWords(String string, String[] words) {
        List<String> wordsList = Arrays.asList(words);

        return wordsList.stream()
                .allMatch(word -> string.toLowerCase().contains(word));
    }
}
