package lab5.communication;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Path;

import lab5.io.console.Console;
import lab5.collection.CollectionManager;
import lab5.collection.ticket.Ticket;
import lab5.communication.exceptions.AlreadyAddedScript;
import lab5.io.connection.*;


public class Handler implements Runnable {
    protected final Console console;
    private final Router router;
    //private final ScriptHandler script;

    public Handler(Console console) {
        this.console = console;
        //this.script = script;
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

        //List<Ticket> tikets = new LinkedList<>();

        //TicketRequest ticketRequest = new TicketRequest(console);

        //Ticket myTicket = ticketRequest.create();
        //tikets.add(myTicket);

        final List<String> args = parts.length > 1 ? Arrays.asList(parts[1].split("\s+")) : Collections.emptyList();

        return new Request(nameCommand, args, console);
    }

    protected void handle(String line) {
        if (line == null) 
            return;
        
        Response response = router.route(parse(line));

        if (response.getMessage() != null && response.getMessage().contains("ScriptExecute")) {
            try (ScriptHandler scriptHandler = new ScriptHandler(console, Path.of(response.getMessage().substring("ScriptExecute".length() + 1)))) {
                scriptHandler.run();
            } catch (AlreadyAddedScript e) {
                console.writeln("File recursion detected: " + e.getMessage());
                return;
            } catch (IOException e) {
                console.writeln("IO Error: " + e.getMessage());
                return;
            }
        }
        printConsole(response);

    }

    @Override
    public void run() {
        console.writeln("Welcome! Let's start working with the collection.");

        CollectionManager.getInstance();

        String line;
        while((line = console.read("Enter the command: \n")) != null) {
            handle(line);
        }
    }
}
