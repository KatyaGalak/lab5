package lab5.io.connection;

import java.util.ArrayList;
import java.util.List;

import lab5.collection.ticket.Ticket;

/**
 * The Response class represents a response to a command request in the application.
 * It encapsulates a message, an optional script, and a list of Ticket objects.
 */
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

    /**
     * Constructs a new Response with the specified message, script, and an empty list of tickets.
     *
     * @param message the response message
     * @param script the script associated with the response
     */
    public Response(final String message, String script) {

        this.message = message;
        this.script = script;
        this.tickets = EMPTY_LIST;
    }

    /**
     * Constructs a new Response with the specified message and list of tickets.
     *
     * @param message the response message
     * @param tickets the list of Ticket objects associated with the response
     */
    public Response(final String message, final List<Ticket> tickets) {

        this.message = message;
        this.tickets = tickets;
    }

    /**
     * Constructs a new Response with the specified message and an array of Ticket objects.
     *
     * @param message the response message
     * @param tickets the Ticket objects associated with the response
     */
    public Response(final String message, final Ticket... tickets) {

        this(message, List.of(tickets));
    }

    /**
     * Constructs a new Response with the specified message and an empty list of tickets.
     *
     * @param message the response message
     */
    public Response(final String message) {

        this(message, EMPTY_LIST);
    }

    /**
     * Creates an empty Response instance.
     *
     * @return an empty Response
     */
    public static Response empty() {

        return new Response(null);
    }
}
