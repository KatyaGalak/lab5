package lab5.communication;

import java.util.HashSet;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lab5.io.console.Console;

public class ScriptHandler extends Handler implements AutoCloseable {
    private static final HashSet<String> openingScripts = new HashSet<>();
    Path scriptPath;

    public ScriptHandler(Console console, Path scriptPath) throws IOException {
        super(console);

        this.scriptPath = scriptPath;

        console.setScriptScanner(new Scanner(Files.newBufferedReader(scriptPath)));
        openingScripts.add(scriptPath.getFileName().toString());

    }

    public static HashSet<String> getOpeningScripts() {
        return openingScripts;
    }

    @Override
    public void close() throws IOException {
        openingScripts.remove(scriptPath.getFileName().toString());
        console.setSimpleScanner();
    }

    @Override
    public void run() {
        try {
            String line;
            while((line = console.read()) != null) {
                handle(line);
            }
        } catch (Exception e) {
            console.writeln("Something went wrong: " + e.getMessage());
        }
    }
}
