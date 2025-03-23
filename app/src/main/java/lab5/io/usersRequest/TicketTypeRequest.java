package lab5.io.usersRequest;

import java.util.function.Predicate;

import lab5.collection.ticket.TicketType;
import lab5.io.console.Console;

public class TicketTypeRequest extends Request<TicketType> {
    public TicketTypeRequest(Console console) {
        super(console);
    }

    @Override
    public TicketType create() {
        Predicate<TicketType> predicateTicketType = x -> (x != null);
        TicketType ticketType = askEnum("Ticket type", TicketType.class, predicateTicketType);

        if (!predicateTicketType.test(ticketType))
            return null;

        return ticketType;
    }
}
