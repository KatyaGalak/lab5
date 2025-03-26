package lab5.communication;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lab5.io.console.Console;
import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.io.connection.*;

/**
 * The Handler class is responsible for processing user commands and managing the interaction
 * between the console and the router. It implements the Runnable interface to allow for
 * concurrent execution.
 */
public class Handler implements Runnable {

    protected final Console console;
    private final Router router;

    /**
     * Constructs a new Handler instance with the specified console.
     *
     * @param console the console used for input and output operations
     */
    public Handler(Console console) {

        this.console = console;
        this.router = Router.getInstance();
    }

    /**
     * Prints the response to the console. If the response contains a message and no tickets,
     * it prints the message. If there are tickets, it prints the message (if present) and
     * then prints each ticket.
     *
     * @param response the response to be printed
     */
    private void printConsole(Response response) {

        if (response.getMessage() != null && !response.getMessage().isBlank() && 
            (response.getTickets() == null || response.getTickets().size() == 0)) {
            console.writeln(response.getMessage());
        } else if (response.getTickets() != null && response.getTickets().size() != 0) {
            if (response.getMessage() != null && !response.getMessage().isBlank()) {
                console.writeln(response.getMessage());
            }
            response.getTickets().stream().map(Ticket::toString).forEach(console::writeln);
        }
    }

    /**
     * Parses the input data into a Request object. The input is split into command name and arguments.
     *
     * @param data the input data to be parsed
     * @return a Request object containing the command name and arguments
     */
    private Request parse(String data) {

        String[] parts = data.split("\s+", 2);

        String nameCommand = parts[0];

        final List<String> args = parts.length > 1 ? Arrays.asList(parts[1].split("\s+")) : Collections.emptyList();

        return new Request(nameCommand, args, console);
    }

    /**
     * Handles the input line by routing it through the router and processing the response.
     * If the response indicates a script execution, it handles the script accordingly.
     *
     * @param line the input line to be handled
     */
    protected void handle(String line) {

        if (line == null) 
            return;
        
        Response response = router.route(parse(line));

        if (response.getMessage() != null && response.getMessage().contains("ScriptExecute")) { 
            String scriptPathString = response.getMessage().substring("ScriptExecute".length() + 1).trim();
            Path scriptPath = Path.of(scriptPathString);

            if (!Files.exists(scriptPath)) {
                console.writeln("IO Error: " + "File: " + scriptPath.getFileName() + " does not exist");
                return;
            }
            if (!Files.isRegularFile(scriptPath)) {
                console.writeln("File: " + scriptPath.getFileName() + " not a regular file");
                return;
            }
            if (!Files.isReadable(scriptPath)) {
                console.writeln("File: " + scriptPath.getFileName() + " cannot be read");
                return;
            }

            if (ScriptHandler.getOpeningScripts().contains(scriptPath.getFileName().toString())) {
                console.writeln("Recursion detected, repeated call: " + scriptPath.getFileName().toString());
                return;
            }

            try (ScriptHandler scriptHandler = new ScriptHandler(console, scriptPath)) {
                scriptHandler.run();
            } catch (IOException e) {
                console.writeln("IO Error: " + e.getMessage());
            }
        }

        printConsole(response);

        if (response.getMessage() != null && response.getMessage().contains("Command Exit")) {
            System.exit(0);
        }
    }

    /**
     * Runs the handler, starting the command processing loop. It prompts the user for input
     * and handles each command until the input is null.
     */
    @Override
    public void run() {

        console.writeln("Welcome! Let's start working with the collection. :)");

        CollectionManager.getInstance();

        String line;
        while((line = console.read("Enter the Command: ")) != null) {
            handle(line);
        }
    }
}
