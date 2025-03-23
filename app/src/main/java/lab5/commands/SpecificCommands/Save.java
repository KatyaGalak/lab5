package lab5.commands.SpecificCommands;

import lab5.collection.CollectionManager;
import lab5.commands.Command;
import lab5.io.connection.*;

public class Save extends Command {

    public Save() {
        super("Save", "Save the collection to file");
    }

    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().saveCollection();
        return new Response("Collection saved :)");
    }
}

