package lab5.commands.SpecificCommands;

import java.util.List;

import lab5.commands.Command;

public final class AddedCommands {
    private static List<Command> addedCommands = List.of(
        new Add(),
        new AddIfMax(),
        new AddIfMin(),
        new Clear(),
        new CountLessThanType(),
        new Exit(),
        new FilterContainsName(),
        new Help(),
        new History(),
        new Info(),
        new MaxById(),
        new RemoveById(),
        new RemoveEquals(),
        new RemoveGreater(),
        new RemoveLower(),
        new Save(),
        new Show(),
        new UpdateById(),
        new ExecuteScript()
    );

    public static List<Command> getAddedCommands() {
        return addedCommands;
    }

    private AddedCommands(){}
}
