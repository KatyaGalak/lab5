package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;
import lab5.collection.TicketComparator;
import lab5.collection.ticket.Ticket;

/**
 * Command to add a new ticket to the collection if it is the maximum ticket.
 * This command compares the new ticket with the current maximum ticket in the collection
 * and adds it only if it is greater.
 */
public class AddIfMax extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};

    /**
     * Constructor for the AddIfMax command.
     * Initializes the command with its name, description, and required arguments.
     */
    public AddIfMax() {
        super("AddIfMax", "Add a new element if it becomes the max after adding it", args);
    }

    /**
     * Executes the command to add a ticket to the collection if it is the maximum.
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

        var maxElemOptional = CollectionManager.getInstance().getTicketCollection().stream().max(new TicketComparator());

        if (!maxElemOptional.isPresent())
            return new Response("The element (Ticket) to add is not specified");

        if (ticket.compareTo(maxElemOptional.get()) > 0) {
            CollectionManager.getInstance().getTicketCollection().add(ticket);
            return new Response("Element was added successfully, now it is the max in the collection.");
        }

        return new Response("Failure to add an element (it is not bigger than all the items in the collection)");
    }
}
