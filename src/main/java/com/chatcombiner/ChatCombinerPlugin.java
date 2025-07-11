package com.chatcombiner;

import com.google.inject.Inject;
import com.google.inject.Provides;
import net.runelite.api.ChatMessageType;
import net.runelite.api.MessageNode;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PluginDescriptor(
        name = "Chat Combiner",
        description = "Combines repeated identical game messages into one with a counter.",
        tags = {"chat", "clean", "repetition"}
)
public class ChatCombinerPlugin extends Plugin {
    private static final Pattern PREFIX_PATTERN = Pattern.compile(
            "^<col=([a-fA-F0-9]{6})>\\((\\d+)x\\)</col>\\s+(.+)$" + // colored prefix
                    "|^\\((\\d+)x\\)\\s+(.+)$" +                             // plain prefix
                    "|^(.+?)\\s+<col=([a-fA-F0-9]{6})>\\((\\d+)x\\)</col>$" + // colored suffix
                    "|^(.+?)\\s+\\((\\d+)x\\)$"                              // plain suffix
    );

    @Inject
    private ChatCombinerConfig config;

    private String lastRawMessage = null;
    private MessageNode lastNode = null;
    private int repeatCount = 1;
    private long lastRepeatTime = 0;

    @Provides
    ChatCombinerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ChatCombinerConfig.class);
    }


    @Subscribe
    public void onChatMessage(ChatMessage event) {
        if (!shouldCombine(event.getType())) {
            return;
        }

        MessageNode currentNode = event.getMessageNode();
        String currentMessage = event.getMessage();
        String rawCurrent = getRawMessage(currentMessage);
        long now = System.currentTimeMillis();

        if (!rawCurrent.equals(lastRawMessage) || (now - lastRepeatTime) > config.resetDelay()) {
            // Message changed or timed out → reset
            lastRawMessage = rawCurrent;
            lastNode = currentNode;
            repeatCount = 1;
            lastRepeatTime = now;
            return;
        }

        repeatCount++;
        lastRepeatTime = now;

        if (repeatCount < config.minRepeatCount()) {
            // Not yet reached the threshold → just let the message appear
            lastNode = currentNode;
            return;
        }

        // We’ve reached or passed the threshold
        Color color = config.counterColor();
        String hexColor = String.format("%06x", color.getRGB() & 0xFFFFFF);
        String formattedMessage = lastNode.getRuneLiteFormatMessage();
        if (formattedMessage == null) {
            formattedMessage = lastNode.getValue();
        }
        String cleanedFormatted = getRawMessage(formattedMessage);
        String counter = "<col=" + hexColor + ">(" + repeatCount + "x)</col>";
        String combinedMessage = getCombinedMessage(counter, cleanedFormatted);

        lastNode.setValue(combinedMessage);
        lastNode.setRuneLiteFormatMessage(combinedMessage);

        // Suppress this message line
        currentNode.setValue("");
        currentNode.setRuneLiteFormatMessage("");
    }

    private String getCombinedMessage(String counter, String message) {
        String combinedMessage;
        if (config.counterPosition() == ChatCombinerConfig.CounterPosition.BEFORE) {
            combinedMessage = counter + " " + message;
        } else {
            combinedMessage = message + " " + counter;
        }
        return combinedMessage;
    }


    private boolean shouldCombine(ChatMessageType type) {
        switch (type) {
            case GAMEMESSAGE:
            case CONSOLE:
            case SPAM:
            case TENSECTIMEOUT:
                return config.combineGameMessages();

            case ITEM_EXAMINE:
            case NPC_EXAMINE:
            case OBJECT_EXAMINE:
            case WELCOME:
            case DIDYOUKNOW:
                return config.combineExamines();

            default:
                return false;
        }
    }

    private String getRawMessage(String message) {
        if (message == null)
            return "";

        // Remove all leading or trailing "(Nx)" tags (colored or plain)
        String pattern = "(<col=[a-fA-F0-9]{6}>)?\\(\\d+x\\)(</col>)?";
        return message
                .replaceAll("^\\s*" + pattern + "\\s*", "")   // prefix
                .replaceAll("\\s*" + pattern + "\\s*$", "")   // suffix
                .trim();
    }
}
