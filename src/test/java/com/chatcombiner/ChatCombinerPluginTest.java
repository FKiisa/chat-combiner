package com.chatcombiner;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ChatCombinerPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(ChatCombinerPlugin.class);
        RuneLite.main(args);
    }
}
