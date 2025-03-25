package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.Request;
import lab5.io.connection.Response;
import lab5.io.usersRequest.TicketRequest;

/**
 * Command to remove tickets from the collection that are greater than the specified ticket.
 */
public class RemoveGreater extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};

    /**
     * Constructor for the RemoveGreater command.
     * Initializes the command with its name and description.
     */
    public RemoveGreater() {
        super("RemoveGreater", "Delete items from the collection that are greater than the specified item", args);
    }

    /**
     * Executes the command to remove tickets from the collection that are greater than the specified ticket.
     *
     * @param request The request containing the ticket information.
     * @return A response indicating the result of the removal operation.
     */
    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not compared and added to the collection");
        }

        CollectionManager.getInstance()
                            .getTicketCollection()
                            .removeIf(ticketCmp -> ticketCmp.compareTo(ticket) > 0);

        return new Response("Collection items greater than the specified value have been deleted (if there were any).");
    }
}
