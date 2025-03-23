package lab5.commands.SpecificCommands.util;

import java.util.LinkedList;
import java.util.List;

public class HistoryManager {
    private static final int NUM_ELEMENTS_HISTORY = 15;

    private LinkedList<String> lastCommands = new LinkedList<>();
    private static HistoryManager historyManager;

    private HistoryManager(){}

    public static HistoryManager getInstance() {
        return historyManager == null ? historyManager = new HistoryManager() : historyManager;
    }

    public void addCommand(String command) {
        lastCommands.add(command);
        if (lastCommands.size() > NUM_ELEMENTS_HISTORY) {
            lastCommands.removeFirst();
        }
    }

    public List<String> getHistory() {
        return lastCommands;
    }
}
