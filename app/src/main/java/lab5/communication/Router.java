package lab5.communication;

import lab5.io.connection.Request;
import lab5.io.connection.Response;
import lab5.commands.SpecificCommands.AddedCommands;
import lab5.commands.SpecificCommands.util.HistoryManager;

/**
 * The Router class is responsible for routing incoming requests to the appropriate command
 * handlers. It implements the Singleton design pattern to ensure that only one instance
 * of the Router exists throughout the application.
 */
public class Router {

    private static Router router;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private Router() {}


    /**
     * Returns the singleton instance of the Router.
     *
     * @return the single instance of Router
     */
    public static Router getInstance() {

        return router == null ? router = new Router() : router;
    }

    /**
     * Routes the given request to the appropriate command handler. If the request is null
     * or the command is blank, an empty response is returned. If the command is found,
     * it is executed and the response is returned. Otherwise, a "Command not found" response
     * is returned.
     *
     * @param request the request to be routed
     * @return the response from the command handler
     */
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
