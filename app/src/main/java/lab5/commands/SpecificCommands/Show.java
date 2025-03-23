package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.Request;
import lab5.io.connection.Response;

public class Show extends Command {

    public Show() {
        super("Show", "Gets collection items in a string representation");
    }

    @Override
    public Response execute(Request agrs) {
        if (CollectionManager.getInstance().getTicketCollection().isEmpty()) 
            return new Response("Collection is empty");
        return new Response("Collection items: ", CollectionManager.getInstance().getList());
    }
}
