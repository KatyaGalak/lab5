package lab5.io.usersRequest;

import lab5.io.console.Console;
import lab5.collection.ticket.Ticket;

import lab5.collection.ticket.Coordinates;
import lab5.collection.ticket.TicketType;
import lab5.collection.ticket.Person;

import java.util.function.Predicate;


public class TicketRequest extends Request<Ticket> {
    private Console console;

    public TicketRequest(Console console) {
        super(console);
        this.console = console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    private Coordinates askCoordinates() {
        return new CoordinatesRequest(console).create();
    }

    private TicketType askTicketType() {
        return new TicketTypeRequest(console).create();
    }

    private Person askPerson() {
        return new PersonRequest(console).create();
    }

    @Override
    public Ticket create() {
        Predicate<String> predicateName = x -> (x != null && x.length() > 0);
        String name = askString("Name Ticket", "The value cannot be missing or have a length of 0", predicateName);

        Predicate<Coordinates> predicateCoordinates = x -> (x != null);
        Coordinates coordinates = askCoordinates();

        Predicate<Double> predicatePrice = x -> (x == null || x > 0);
        Double price = askNumericValue("Ticket price", "The value must be greater then 0", predicatePrice, Double.class);

        Predicate<Boolean> predicateRefundable = x -> true;
        Boolean refubdable = askBoolean("Returnability of the Ticket", "TRUE or FALSE", x -> (x.isEmpty() || x == null || x.equalsIgnoreCase("TRUE") || x.equalsIgnoreCase("FALSE")));

        Predicate<TicketType> predicateTicketType = x -> (x != null);
        TicketType ticketType = askTicketType();

        Predicate<Person> predicatePerson = x -> (x != null);
        Person person = askPerson();

        if (!predicateName.test(name))
        return null;

        if (!predicateCoordinates.test(coordinates))
            return null;

        if (!predicatePrice.test(price))
            return null;

        if (!predicateRefundable.test(refubdable))
            return null;

        if (!predicateTicketType.test(ticketType))
            return null;

        if (!predicatePerson.test(person))
            return null;

        Ticket ticket = new Ticket(name, coordinates, price, refubdable, ticketType, person);
 
        return ticket;
    }
}
