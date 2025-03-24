package lab5.commands.SpecificCommands;

import java.util.List;

import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.commands.Command;
import lab5.io.connection.*;
import lab5.io.usersRequest.TicketRequest;
import lab5.commands.SpecificCommands.util.CreateRandomTicket;

public class AddRandom extends Command {
    static final String[] args = new String[]{"cnt"};
    public AddRandom() {
        super("AddRandom", "Add a set number of random tickets", args);
    }

    @Override
    public Response execute(Request request) {

        int cntTickets = 0;

        if (request.getArgs() == null || request.getArgs().isEmpty()) {
            cntTickets = 1;
        } else if (!isNumeric(request.getArgs().get(0))) {
            return new Response("The transferred arg is not a number");
        } else {
            cntTickets = Integer.parseInt(request.getArgs().get(0));
        }
        try {
            List<Ticket> randomTickets = CreateRandomTicket.generateRandomTicket(cntTickets);
            CollectionManager.getInstance().getTicketCollection().addAll(randomTickets);
        } catch (IllegalArgumentException e) {
            return new Response("The problem with the generated data");
        }
        
        return new Response("Random Tickets added (" + cntTickets + ").");
    }
}