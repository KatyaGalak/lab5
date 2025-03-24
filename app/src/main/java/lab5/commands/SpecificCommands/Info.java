package lab5.commands.SpecificCommands;

import java.util.TreeSet;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

import lab5.collection.ticket.Ticket;

/**
 * Command to retrieve and display information about the collection.
 */
public class Info extends Command {
    /**
     * Constructor for the Info command.
     * Initializes the command with its name and description.
     */


    public Info() {
        super("Info", "Gets information about the collection");
    }

    /**
     * Executes the command to retrieve information about the collection.
     *
     * @param request The request containing the arguments for the command.
     * @return A response containing the type, initialization date, and size of the collection.
     */
    @Override
    public Response execute(Request request) {

        TreeSet<Ticket> collection = CollectionManager.getInstance().getTicketCollection();

        return new Response(String.join(System.lineSeparator(), new String[]{"type: " + collection.getClass(),
                                                                    "initialization date: " + CollectionManager.getInstance().getCreationDate(),
                                                                    "size: " + collection.size()}));
    }
}
