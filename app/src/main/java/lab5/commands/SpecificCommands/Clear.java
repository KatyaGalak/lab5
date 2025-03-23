package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

public class Clear extends Command {

    public Clear() {
        super("Clear", "Clear the collection");
    }

    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().getTicketCollection().clear();

        return new Response("Collection has been successfully cleared.");
    }
}

