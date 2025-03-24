package lab5.communication;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lab5.io.console.Console;

public class ScriptHandler extends Handler implements AutoCloseable {
    private static final Stack<String> openingScripts = new Stack<>();
    private static Stack<Scanner> scannerStack = new Stack<>();
    Path scriptPath;

    public ScriptHandler(Console console, Path scriptPath) throws IOException {
        super(console);

        this.scriptPath = scriptPath;

        if (console.isFileScanner()) {
            scannerStack.add(console.getReader());
            openingScripts.add("PRE READER");
        }

        Scanner curScanner = new Scanner(Files.newBufferedReader(scriptPath));
        console.setScriptScanner(curScanner);

        //System.out.println("\nADD = " + scriptPath.getFileName().toString() + "\n");
       // scannerStack.add(curScanner);
        //openingScripts.add(scriptPath.getFileName().toString());

    }

    public static Stack<String> getOpeningScripts() {
        return openingScripts;
    }

    @Override
    public void close() throws IOException {
        if (!scannerStack.isEmpty()) {
           // System.out.println("\nCLOSE = " + openingScripts.peek() + " " + openingScripts.size());
            console.setScriptScanner(scannerStack.pop());
            openingScripts.pop();

        } else {
            console.setSimpleScanner();
            //System.out.println("YAAAAAAAAAAAAA");
        }
    }

    @Override
    public void run() {
        try {
            console.writeln("Executing script: " + scriptPath.getFileName());

            String line;
            while((line = console.read()) != null) {
               // System.out.println("\nLINE = " + line + "\n");
                handle(line);
            }
        } catch (Exception e) {
            console.writeln("Something went wrong: " + e.getMessage());
        }
    }
}
