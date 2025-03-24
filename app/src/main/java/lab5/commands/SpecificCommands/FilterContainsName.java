package lab5.commands.SpecificCommands;

import java.util.List;
import java.util.ArrayList;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to filter tickets whose names contain a specified substring.
 */
public class FilterContainsName extends Command {
    /**
     * Constructor for the FilterContainsName command.
     * Initializes the command with its name, description, and arguments.
     */

    static final String[] args = new String[]{"name"};
    public FilterContainsName() {
        super("FilterContainsName", "Get the elements whose name contains the specified string", args);
    }

    /**
     * Executes the command to filter tickets based on the provided request.
     *
     * @param request The request containing the arguments for the command.
     * @return A response containing the filtered tickets or an error message.
     */
    @Override
    public Response execute(Request request) {

        if (request.getArgs().get(0).isEmpty()) {
            return new Response("Passed string is empty");
        }

        List<Ticket> nameWithSubstr = new ArrayList<>();

        for (Ticket ticket : CollectionManager.getInstance().getTicketCollection()) {
            if (ticket.getName().contains(request.getArgs().get(0))) 
                nameWithSubstr.add(ticket);
        }

        if (nameWithSubstr.isEmpty()) {
            return new Response("No elements in the collection whose name contains substring: " + request.getArgs().get(0));
        }

        return new Response("Elements with the specified substr (" + request.getArgs().get(0) + ") in their name", nameWithSubstr);
    }
}
