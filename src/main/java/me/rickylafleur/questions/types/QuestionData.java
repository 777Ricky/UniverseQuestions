package me.rickylafleur.questions.types;

import org.bukkit.configuration.ConfigurationSection;

public class QuestionData {
    private final String identifier;
    private final String response;

    public QuestionData(String identifier, String response) {
        this.identifier = identifier;
        this.response = response;
    }

    public static QuestionData load(String identifier, ConfigurationSection config) {
        return new QuestionData(
                identifier,
                config.getString("response", "no response")
        );
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getResponse() {
        return response;
    }
}
