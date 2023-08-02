package me.rickylafleur.questions.data;

import me.rickylafleur.questions.QuestionsPlugin;
import me.rickylafleur.questions.types.QuestionData;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class QuestionManager {
    private final QuestionsPlugin plugin;

    private final Map<String, QuestionData> questionDataMap = new HashMap<>();

    public QuestionManager(QuestionsPlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        ConfigurationSection config = plugin.getConfig();

        questionDataMap.clear();

        config.getKeys(false).stream()
                .filter(config::isConfigurationSection)
                .forEach(key -> {
                    try {
                        questionDataMap.put(key, QuestionData.load(key, config.getConfigurationSection(key)));
                    } catch (Exception e) {
                        plugin.getLogger().log(Level.WARNING, String.format("Failed to load question data for '%s'!", key), e);
                    }
                });
    }

    public Collection<QuestionData> getQuestions() {
        return questionDataMap.values();
    }
}
