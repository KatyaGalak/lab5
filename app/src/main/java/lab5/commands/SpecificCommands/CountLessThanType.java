package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.collection.ticket.TicketType;

import lab5.io.connection.*;

public class CountLessThanType extends Command {
    static final String[] args = new String[]{"type"};
    public CountLessThanType() {
        super("CountLessThanType", "Count the number of elements with a type less than the type specified by the user", args);
    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs().get(0).isEmpty()) {
            return new Response("Empty ticket type is set");
        }
        try {
            TicketType ticketType = TicketType.valueOf(request.getArgs().get(0).toUpperCase());

            int cnt = 0;

            for (Ticket ticket : CollectionManager.getInstance().getTicketCollection()) {
                cnt += (ticket.getType().getValue() < ticketType.getValue()) ? 1 : 0;
            }

            return new Response("Number of items with a ticket type less than the specified type: " + cnt);
        } catch (IllegalArgumentException e) {
            return new Response("There is no ticket type: " + request.getArgs().get(0));
        }
        
    }
}

