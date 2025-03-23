package lab5.communication;

import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lab5.io.console.Console;
import lab5.collection.CollectionManager;
import lab5.communication.exceptions.AlreadyAddedScript;

public class ScriptHandler extends Handler implements AutoCloseable {
    private static final HashSet<String> openingScripts = new HashSet<>();
    Path scriptPath;

    public ScriptHandler(Console console, Path scriptPath) throws AlreadyAddedScript, IOException {
        super(console);

        this.scriptPath = scriptPath;

        if (!Files.exists(scriptPath)) {
            throw new FileNotFoundException("File: " + scriptPath.getFileName() + " does not exist");
        }
        if (!Files.isRegularFile(scriptPath)) {
            throw new IOException("File: " + scriptPath.getFileName() + " not a regular file");
        }
        if (!Files.isReadable(scriptPath)) {
            throw new IOException("File: " + scriptPath.getFileName() + " cannot be read");
        }

        if (openingScripts.contains(scriptPath.getFileName().toString())) {
            throw new AlreadyAddedScript(scriptPath.getFileName().toString());
        }

        console.setScriptScanner(new Scanner(Files.newBufferedReader(scriptPath)));
        openingScripts.add(scriptPath.getFileName().toString());

    }

    @Override
    public void close() throws IOException {
        openingScripts.remove(scriptPath.getFileName().toString());
        console.setSimpleScanner();
    }

    @Override
    public void run() {
        CollectionManager.getInstance();

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
