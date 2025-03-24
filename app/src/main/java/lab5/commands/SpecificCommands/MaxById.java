package lab5.commands.SpecificCommands;

import java.util.Comparator;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to retrieve and display the ticket with the maximum ID.
 */
public class MaxById extends Command {
    /**
     * Constructor for the MaxById command.
     * Initializes the command with its name and description.
     */


    public MaxById() {
        super("MaxById", "Get an item with the max ID");
    }

    /**
     * Executes the command to retrieve the ticket with the maximum ID.
     *
     * @param request The request containing the arguments for the command.
     * @return A response containing the ticket with the maximum ID or a message if the collection is empty.
     */
    @Override
    public Response execute(Request request) {

        var maxElemOptional = CollectionManager.getInstance().getTicketCollection().stream().max(Comparator.comparing(Ticket::getId));

        if (!maxElemOptional.isPresent()) {
            return new Response("The collection is empty (no element with max ID)");
        }

        Ticket maxElem = maxElemOptional.get();

        return new Response("Element with max ID: ", maxElem);
    }
}
