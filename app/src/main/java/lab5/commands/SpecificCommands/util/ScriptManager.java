/*package lab5.commands.SpecificCommands.util;

import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ScriptManager {
    private static final Stack<String> openingScripts = new Stack<>();
    private static final Stack<Scanner> openingScriptsScanners = new Stack<>();

    public static void addScript(String path) throws FileNotFoundException {
        openingScripts.push(new File(path).getAbsolutePath());
        openingScriptsScanners.push(new Scanner(new File(path)));
    }

    public static boolean checkRecursive(String path) {
        return openingScripts.contains(new File(path).getAbsolutePath());
    }

    public static void popScript() {
        openingScripts.pop();
        openingScriptsScanners.pop();
    }

    public static Scanner getLastScriptScanner() {
        return openingScriptsScanners.lastElement();
    }
}*/
