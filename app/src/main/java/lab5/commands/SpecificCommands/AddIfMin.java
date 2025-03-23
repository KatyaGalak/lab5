package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;
import lab5.collection.TicketComparator;
import lab5.collection.ticket.Ticket;

public class AddIfMin extends Command {
    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};
    public AddIfMin() {
        super("AddIfMin", "Add a new element if it becomes the min after adding it", args);
    }

    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not compared and added to the collection");
        }

        var maxElemOptional = CollectionManager.getInstance().getTicketCollection().stream().min(new TicketComparator());

        if (!maxElemOptional.isPresent())
            return new Response("The element (Ticket) to add is not specified");

        if (ticket.compareTo(maxElemOptional.get()) < 0) {
            CollectionManager.getInstance().getTicketCollection().add(ticket);
            return new Response("Element was added successfully, now it is the min in the collection.");
        }

        return new Response("Failure to add an element (it is not smaller than all the items in the collecion)");
    }
}
