package lab5.io.console;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StandartConsole implements Console {
    private static Scanner reader = new Scanner(System.in);
    //private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean isScript = false;

    //private static Scanner fileScanner = null;

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
    }

    @Override
    public void write(String data) {
        try {
            writer.append(data);
            writer.flush();
        } catch (IOException e) {}
    }

    @Override
    public void writeln(String data) {
        try {
            writer.append(data).append(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {}
    }

    @Override
    public String read() {
        try {
            /*if (fileScanner != null) {
                return fileScanner.nextLine();
            }

            return reader.readLine();*/
            return reader.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public String read(String prompt) {
        writeln(prompt);

        return read();
    }

    /*public void switchFileScanner(Scanner scanner) {
        fileScanner = scanner;
    }

    public void switchConsoleScanner() {
        fileScanner = null;
    }*/

    @Override
    public void setScriptScanner(Scanner scanner) {
        reader = scanner;
        isScript = true;
    }

    @Override
    public boolean isFileScanner() {
        return isScript;
    }

    @Override
    public void setSimpleScanner() {
        isScript = false;
        reader = new Scanner(System.in);
    }
}
