package lab5.commands.SpecificCommands;

import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to execute a script file containing a series of commands.
 * This command reads the specified script file and executes the commands within it.
 */
public class ExecuteScript extends Command {
    static final String[] args = new String[]{"path"};

    /**
     * Constructor for the ExecuteScript command.
     * Initializes the command with its name and description.
     */
    public ExecuteScript() {
        super("ExecuteScript", "Execute script", args);
    }

    /**
     * Executes the command to run the specified script.
     *
     * @param request The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs() == null || request.getArgs().isEmpty()) 
            return new Response("The path to the script was not passed.");
        
        return new Response("ScriptExecute " + request.getArgs().get(0));
        /* Additional logic for executing the script can be implemented here */
    }
}
