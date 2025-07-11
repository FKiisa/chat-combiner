package com.chatcombiner;

import net.runelite.client.config.*;

import java.awt.Color;

@ConfigGroup("chatcombiner")
public interface ChatCombinerConfig extends Config {
    @ConfigItem(
            keyName = "counterColor",
            name = "Counter Color",
            description = "Color of the repeated message counter"
    )
    default Color counterColor() {
        return Color.ORANGE;
    }

    @ConfigItem(
            keyName = "combineGameMessages",
            name = "Game Messages",
            description = "Combine repeated game messages (e.g. skill actions, notifications)"
    )
    default boolean combineGameMessages() {
        return true;
    }

    @ConfigItem(
            keyName = "combineExamines",
            name = "Examines & Info",
            description = "Combine repeated item/NPC examines and welcome/info messages"
    )
    default boolean combineExamines() {
        return false;
    }

    @ConfigItem(
            keyName = "minRepeatCount",
            name = "Minimum Repeat Count",
            description = "Only combine after this number of repeated messages"
    )
    @Range(min = 2, max = 20)
    default int minRepeatCount() {
        return 2;
    }

    @ConfigItem(
            keyName = "resetDelay",
            name = "Reset After (ms)",
            description = "Reset repeat counter if no repeats occur within this time (ms)"
    )
    @Units(Units.MILLISECONDS)
    @Range(min = 0, max = 60000)
    default int resetDelay() {
        return 15000;
    }

    enum CounterPosition { BEFORE, AFTER }

    @ConfigItem(
            keyName = "counterPosition",
            name = "Counter Position",
            description = "Place counter before or after the message"
    )
    default CounterPosition counterPosition() { return CounterPosition.BEFORE; }
}
