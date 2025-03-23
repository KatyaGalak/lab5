package lab5.commands.SpecificCommands;

import lab5.commands.Command;
import lab5.io.connection.*;

public class Help extends Command {

    public Help() {
        super("Help", "Get help on available commands");
    }

    @Override
    public Response execute(Request request) {
        StringBuilder res = new StringBuilder();

        for (Command command : AddedCommands.getAddedCommands()) {
            res.append(command.getName()).append(" - ").append(command.getDescription()).append(System.lineSeparator());
            if (command.getArgs() != Command.EMPTY_ARGUMENTS) {
                res.append("args: (").append(String.join(", ", command.getArgs())).append(")").append(System.lineSeparator());
            }
            res.append(System.lineSeparator());
        }


        return new Response(res.toString());
    }
}

