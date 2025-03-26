package lab5.io.connection;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import lab5.io.console.Console;

@ToString
@EqualsAndHashCode
/**
 * The Request class represents a user command request in the application. It encapsulates
 * the command string, its associated arguments, and a console instance for output.
 */
public class Request {

    @Getter
    private String command;

    @Getter
    private List<String> args;

    @Getter
    Console console;

    /**
     * Constructs a new Request with the specified command, arguments, and console.
     *
     * @param command the command string
     * @param args the list of arguments associated with the command
     * @param console the console instance for output
     */
    public Request(final String command, final List<String> args, final Console console) {

        this.command = command;
        this.args = args;
        this.console = console;
    }

    /**
     * Constructs a new Request with the specified command and console.
     *
     * @param command the command string
     * @param console the console instance for output
     */
    public Request(final String command, final Console console) {

        this.command = command;
        this.console = console;
    }
}
