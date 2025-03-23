package lab5.commands.SpecificCommands;

import java.util.Comparator;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;

public class MaxById extends Command {

    public MaxById() {
        super("MaxById", "Get an item with the max ID");
    }

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
