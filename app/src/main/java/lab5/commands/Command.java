package lab5.commands;

import lombok.Getter;

import lab5.io.connection.*;

public abstract class Command {
    public static final String[] EMPTY_ARGUMENTS = new String[0];
    private static final int EMPTY_CNT_ARGUMENTS = 0;

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final String[] args;
    @Getter
    private final int cntArgs;

    public Command(final String name, final String description, 
                    final String[] args, final int cntArgs) {
        this.name = name;
        this.description = description;
        this.args = args;
        this.cntArgs = cntArgs;
    }

    public Command(final String name, final String description, 
                    final String[] args) {
        this(name, description, args, args.length);
    }

    public Command(final String name, final String description) {
        this(name, description, EMPTY_ARGUMENTS, EMPTY_CNT_ARGUMENTS);
    }

    public abstract Response execute(Request request);

    protected boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) 
            return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
