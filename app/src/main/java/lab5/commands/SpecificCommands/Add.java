package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;

/**
 * Command to add a new ticket to the collection.
 * This command takes user input to create a new Ticket object and adds it to the collection.
 */
public class Add extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};

    /**
     * Constructor for the Add command.
     * Initializes the command with its name, description, and required arguments.
     */
    public Add() {
        super("Add", "Add a new element to the collection", args);
    }

    /**
     * Executes the command to add a ticket to the collection.
     *
     * @param request The request containing the command arguments.
     * @return Response indicating the result of the command execution.
     */
    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not added to the collection");
        }

        CollectionManager.getInstance().getTicketCollection().add(ticket);
        return new Response("Ticket added");
    }
}
