package lab5.commands.SpecificCommands;

import java.util.TreeSet;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

import lab5.collection.ticket.Ticket;

public class Info extends Command {

    public Info() {
        super("Info", "Gets information about the collection");
    }

    @Override
    public Response execute(Request request) {
        TreeSet<Ticket> collection = CollectionManager.getInstance().getTicketCollection();

        return new Response(String.join(System.lineSeparator(), new String[]{"type: " + collection.getClass(),
                                                                    "initialization date: " + CollectionManager.getInstance().getCreationDate(),
                                                                    "size: " + collection.size()}));
    }
}
