package lab5.commands.SpecificCommands;

import java.io.IOException;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.Request;
import lab5.io.connection.Response;
import lab5.io.usersRequest.TicketRequest;


public class UpdateById extends Command {
    static final String[] args = new String[]{"id"};
    public UpdateById() {
        super("UpdateByID", "Update the item with the passed ID.", args);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) 
            return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs() == null || request.getArgs().isEmpty()) {
            return new Response("ID of the item to be deleted is not set");
        }

        if (!isNumeric(request.getArgs().get(0))) {
            return new Response("Received ID is not a number");
        }

        TicketRequest ticketRequest = new TicketRequest(request.getConsole());

        Ticket ticket = ticketRequest.create();

        if (ticket == null) {
            return new Response("[Error] Ticket object was created with an error. The item was not updated");
        }

        long ID = Long.parseLong(request.getArgs().get(0));
        if (!CollectionManager.getInstance()
                                .getTicketCollection()
                                .stream()
                                .anyMatch(ticketCmp -> ticketCmp.getId() == ID))
            return new Response("There is no ticket with this ID");

        CollectionManager.getInstance().getTicketCollection().remove(
                                                                CollectionManager.getInstance().getTicketCollection()
                                                                .stream()
                                                                .filter(ticketCmp -> ticketCmp.getId() == ID).findFirst().get());
        try {
            ticket.setId(ID);
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            return new Response("Incorrect number was passed to set the ID: " + ID);
        }
        
        CollectionManager.getInstance().getTicketCollection().add(ticket);

        return new Response("The element with ID: " + ID + " has been updated");
    }
}
