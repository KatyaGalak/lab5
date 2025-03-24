package lab5.io.console;

import lab5.io.IOHandler;

import java.util.Scanner;

public interface Console extends IOHandler<String> {
    public String read(String prompt);
    public void writeln(String data);
    public boolean isFileScanner();
    public void setScriptScanner(Scanner scanner);
    public void setSimpleScanner();

    public Scanner getReader();
}
