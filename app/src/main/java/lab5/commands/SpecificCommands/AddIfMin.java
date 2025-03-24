package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;
import lab5.collection.TicketComparator;
import lab5.collection.ticket.Ticket;

/**
 * Command to add a new ticket to the collection if it is the minimum ticket.
 * This command compares the new ticket with the current minimum ticket in the collection
 * and adds it only if it is smaller.
 */
public class AddIfMin extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};

    /**
     * Constructor for the AddIfMin command.
     * Initializes the command with its name, description, and required arguments.
     */
    public AddIfMin() {
        super("AddIfMin", "Add a new element if it becomes the min after adding it", args);
    }

    /**
     * Executes the command to add a ticket to the collection if it is the minimum.
     *
     * @param request The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not compared and added to the collection");
        }

        var minElemOptional = CollectionManager.getInstance().getTicketCollection().stream().min(new TicketComparator());

        if (!minElemOptional.isPresent())
            return new Response("The element (Ticket) to add is not specified");

        if (ticket.compareTo(minElemOptional.get()) < 0) {
            CollectionManager.getInstance().getTicketCollection().add(ticket);
            return new Response("Element was added successfully, now it is the min in the collection.");
        }

        return new Response("Failure to add an element (it is not smaller than all the items in the collection)");
    }
}
