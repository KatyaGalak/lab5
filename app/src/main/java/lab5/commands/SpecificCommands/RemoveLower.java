package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.Request;
import lab5.io.connection.Response;
import lab5.io.usersRequest.TicketRequest;

public class RemoveLower extends Command {

    static final String[] args = new String[]{"name", "x", "y", "price", "refundable", "type", "person"};
    public RemoveLower() {
        super("RemoveLower", "Delete items from the collection that are smaller than the specified item", args);
    }

    @Override
    public Response execute(Request request) {
        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not compared and added to the collection");
        }

        CollectionManager.getInstance()
                            .getTicketCollection()
                            .removeIf(ticketCmp -> ticketCmp.compareTo(ticket) < 0);

        return new Response("Collection items less than the specified value have been deleted (If there were any).");
    }
}

