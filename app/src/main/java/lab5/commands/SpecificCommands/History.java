package lab5.commands.SpecificCommands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lab5.commands.Command;
import lab5.commands.SpecificCommands.util.HistoryManager;
import lab5.io.connection.*;

public class History extends Command {

    public History() {
        super("History", "Get the last 15 commands");
    }

    @Override
    public Response execute(Request request) {
        List<String> history = HistoryManager.getInstance().getHistory();

        String historyWithLineNumbers = IntStream.range(0, history.size())
                .mapToObj(i -> (i + 1) + ": " + history.get(i))
                .collect(Collectors.joining(System.lineSeparator()));

        return new Response(historyWithLineNumbers);
    }
}

