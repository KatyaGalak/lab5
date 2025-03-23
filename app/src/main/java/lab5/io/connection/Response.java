package lab5.io.connection;

import java.util.ArrayList;
import java.util.List;

import lab5.collection.ticket.Ticket;

public class Response {
    private static final List<Ticket> EMPTY_LIST = new ArrayList<>();;

    private final String message;
    private String script;
    private final List<Ticket> tickets;

    public String getMessage() {
        return message;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public String getScript() {
        return script;
    }

    public Response(final String message, String script) {
        this.message = message;
        this.script = script;
        this.tickets = EMPTY_LIST;
    }

    public Response(final String message, final List<Ticket> tickets) {
        this.message = message;
        this.tickets = tickets;
    }

    public Response(final String message, final Ticket... tickets) {
        this(message, List.of(tickets));
    }

    public Response(final String message) {
        this(message, EMPTY_LIST);
    }

    public static Response empty() {
        return new Response(null);
    }
}
