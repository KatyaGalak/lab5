package lab5.commands.SpecificCommands;

import java.util.Collection;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.PersonRequest;

/**
 * Command to clear the collection of tickets.
 * This command removes all tickets from the collection, effectively resetting it.
 */
public class Clear extends Command {

    /**
     * Constructor for the Clear command.
     * Initializes the command with its name and description.
     */
    public Clear() {
        super("Clear", "Clear the collection");
    }

    /**
     * Executes the command to clear the ticket collection.
     *
     * @param request The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request request) {
        Collection<Ticket> tickets = CollectionManager.getInstance().getTicketCollection();

        for (Ticket ticket : tickets) {
            PersonRequest.deletePassportID(ticket.getPerson().getPassportID());
        }

        CollectionManager.getInstance().getTicketCollection().clear();

        return new Response("Collection has been successfully cleared.");
    }
}
