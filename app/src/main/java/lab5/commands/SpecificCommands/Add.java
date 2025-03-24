package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;


public class Add extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};
    public Add() {
        super("Add", "Add a new element to the collection", args);
    }

    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not added to the collection");
        }

        CollectionManager.getInstance().getTicketCollection().add(ticket);
        System.out.println("SIZE after add: " + CollectionManager.getInstance().getTicketCollection().size());
        return new Response("Ticket added");
    }
}
