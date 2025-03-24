package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.Request;
import lab5.io.connection.Response;

/**
 * Command to display the items in the collection.
 * This command retrieves the string representation of all items in the collection.
 */
public class Show extends Command {

    /**
     * Constructor for the Show command.
     * Initializes the command with its name and description.
     */
    public Show() {
        super("Show", "Gets collection items in a string representation");
    }

    /**
     * Executes the command to show the items in the collection.
     *
     * @param args The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request args) {
        if (CollectionManager.getInstance().getTicketCollection().isEmpty()) 
            return new Response("Collection is empty");
        return new Response("Collection items: ", CollectionManager.getInstance().getList());
    }
}
