package lab5.io.connection;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import lab5.io.console.Console;

@ToString
@EqualsAndHashCode
public class Request {
    @Getter
    private String command;

    @Getter
    private List<String> args;

    @Getter
    Console console;

    //@Getter
    //private List<Ticket> tickets;

    public Request(final String command, final List<String> args, final Console console) {
        this.command = command;
        this.args = args;
        this.console = console;
        //this.tickets = tickets;
    }

    public Request(final String command, final Console console) {
        this.command = command;
        this.console = console;
    }
}
