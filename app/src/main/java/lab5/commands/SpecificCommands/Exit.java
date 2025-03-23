package lab5.commands.SpecificCommands;

import lab5.commands.Command;
import lab5.io.connection.*;

public class Exit extends Command {

    public Exit() {
        super("Exit", "Program shutdown");
    }

    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("Exiting the program");
    }
}

