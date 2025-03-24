package lab5.commands.SpecificCommands;

import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to exit the application.
 * This command terminates the program when executed.
 */
public class Exit extends Command {

    /**
     * Constructor for the Exit command.
     * Initializes the command with its name and description.
     */
    public Exit() {
        super("Exit", "Program shutdown");
    }

    /**
     * Executes the command to terminate the application.
     *
     * @param request The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("Exiting the program");
    }
}
