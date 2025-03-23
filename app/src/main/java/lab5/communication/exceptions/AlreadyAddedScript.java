package lab5.communication.exceptions;

public class AlreadyAddedScript extends Exception {
    public AlreadyAddedScript(String nameScript) {
        super(nameScript);
    }
}   
