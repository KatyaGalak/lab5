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

public class Handler implements Runnable {
    protected final Console console;
    private final Router router;

    public Handler(Console console) {
        this.console = console;
        this.router = Router.getInstance();
    }

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

    private Request parse(String data) {
        String[] parts = data.split("\s+", 2);

        String nameCommand = parts[0];

        final List<String> args = parts.length > 1 ? Arrays.asList(parts[1].split("\s+")) : Collections.emptyList();

        return new Request(nameCommand, args, console);
    }

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
                console.writeln(scriptPath.getFileName().toString());
                return;
            }

            try (ScriptHandler scriptHandler = new ScriptHandler(console, scriptPath)) {
                //System.out.println("PRE RUN = " + scriptPathString);
                scriptHandler.run();
                //System.out.println("AFTER RUN = " + scriptPathString);
            } catch (IOException e) {
                console.writeln("IO Error: " + e.getMessage());
            }
        }

        printConsole(response);

    }

    @Override
    public void run() {
        console.writeln("Welcome! Let's start working with the collection.");

        CollectionManager.getInstance();

        String line;
        while((line = console.read("Enter the Command: ")) != null) {
           // System.out.println("\nLINEE = " + line + "\n");
            handle(line);
        }
    }
}
