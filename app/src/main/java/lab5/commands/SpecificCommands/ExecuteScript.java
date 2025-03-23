package lab5.commands.SpecificCommands;

import lab5.commands.Command;
import lab5.io.connection.*;

public class ExecuteScript extends Command {
    static final String[] args = new String[]{"path"};
    public ExecuteScript() {
        super("ExecuteScript", "Execute script", args);
    }

    @Override
    public Response execute(Request request) {
        if (request.getArgs() == null || request.getArgs().isEmpty()) 
            return new Response("The path to the script was not passed.");
        return new Response("ScriptExecute " + request.getArgs().get(0));
        /*if (request.getArgs() == null || request.getArgs().isEmpty()) 
            return new Response("The path to the script was not passed.");
        
        Path filePath = Paths.get(request.getArgs().get(0));

        if (!Files.exists(filePath)) {
            return new Response("File: " + filePath.getFileName() + " does not exist");
        }
        if (!Files.isRegularFile(filePath)) {
            return new Response("File: " + filePath.getFileName() + " not a regular file");
        }
        if (!Files.isReadable(filePath)) {
            return new Response("File: " + filePath.getFileName() + " cannot be read");
        }

        try {
            ScriptManager.addScript(filePath.toString());

            Scanner scanner;
            while ((scanner = ScriptManager.getLastScriptScanner()) != null) {
                request.getConsole().setScriptScanner(scanner);

                String data = scanner.nextLine();
                String[] parts = data.split("\s+", 2);

                if (parts[0].equalsIgnoreCase("execute_script")) {
                    if (parts.length == 1) {
                        return new Response("No argument was passed for the 'execute_script' command (the name of the script)");
                    }

                    if (ScriptManager.checkRecursive(parts[1])) {
                        return new Response("Recursion detected. Script execution aborted (" + parts[1] + ").");
                    }
                }

                final List<String> args = parts.length > 1 ? Arrays.asList(parts[1].split("\s+")) : Collections.emptyList();

                //Request newRequest = new Request(parts[0], parts[1], request.getConsole());


            }
        }*/

        /*try {
            ScriptManager.addScript(filePath.toString());
            Scanner scanner;
            while ((scanner = ScriptManager.getLastScriptScanner()) != null) {

            }
        }*/

        /*try (FileHandler file = new FileHandler(filePath)) {
            StringBuilder script = new StringBuilder();
            while (file.scannerHasNext()) {
                script.append(file.read()).append(System.lineSeparator());
            }
            return new Response("Add script body", script.toString());
        } catch (Exception e) {
            return new Response("Error when working with ScriptFile");
        }*/
    }
}
