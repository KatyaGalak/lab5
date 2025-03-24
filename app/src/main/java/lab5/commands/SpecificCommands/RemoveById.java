package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

/**
 * Command to remove a ticket from the collection by its ID.
 */
public class RemoveById extends Command {
    /**
     * Constructor for the RemoveById command.
     * Initializes the command with its name and description.
     */

    static final String[] args = new String[]{"id"};
    public RemoveById() {
        super("RemoveByID", "Delete an item by its ID", args);
    }

    /**
     * Executes the command to remove a ticket by its ID.
     *
     * @param request The request containing the arguments for the command.
     * @return A response indicating the result of the removal operation.
     */
    @Override
    public Response execute(Request request) {

        if (request.getArgs() == null || request.getArgs().isEmpty()) {
            return new Response("ID of the item to be deleted is not set");
        }

        if (!isNumeric(request.getArgs().get(0))) {
            return new Response("Received ID is not a number");
        }

        final long delID = Long.parseLong(request.getArgs().get(0));
        if (!CollectionManager.getInstance()
                                .getTicketCollection()
                                .stream()
                                .anyMatch(ticket -> ticket.getId() == delID))
            return new Response("There is no ticket with this ID");

        CollectionManager.getInstance().getTicketCollection().removeIf(ticket -> ticket.getId() == delID);

        return new Response("Ticket with ID: " + delID + " deleted");
    }
}
