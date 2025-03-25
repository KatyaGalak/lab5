package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to save the current state of the collection to a file.
 */
public class Save extends Command {
    /**
     * Constructor for the Save command.
     * Initializes the command with its name and description.
     */


    public Save() {
        super("Save", "Save the collection to file");
    }

    /**
     * Executes the command to save the current state of the collection to a file.
     *
     * @param request The request containing the necessary information for saving.
     * @return A response indicating the result of the save operation.
     */

    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().saveCollection();
        return new Response("Collection saved :)");
    }

}
