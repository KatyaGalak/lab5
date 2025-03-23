package lab5.communication;

import lab5.io.connection.Request;
import lab5.io.connection.Response;
import lab5.commands.SpecificCommands.AddedCommands;
import lab5.commands.SpecificCommands.util.HistoryManager;

public class Router {
    private static Router router;

    private Router() {}

    public static Router getInstance() {
        return router == null ? router = new Router() : router;
    }

    public Response route(Request request) {
        if (request == null || request.getCommand() == null 
            || request.getCommand().isBlank()) {
                return Response.empty();
        }

        return AddedCommands.getAddedCommands().stream()
                                    .filter(name -> name.getName().equalsIgnoreCase(request.getCommand()))
                                    .findFirst()
                                    .map(temp -> {
                                        HistoryManager.getInstance().addCommand(temp.getName());
                                        return temp.execute(request);
                                    }
                                    ).orElse(new Response("Command not found"));
    }
}
