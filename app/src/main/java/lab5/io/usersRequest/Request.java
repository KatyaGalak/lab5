package lab5.io.usersRequest;

import lab5.io.console.Console;

import java.util.function.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Request<T> {
    //private Scanner scanner = ScannerManager.getScanner();
    private Console console;

    public Request(Console console) {
        this.console = console;
    }

    public abstract T create();

    public <S extends Number> S askNumericValue(String name, String limitations, Predicate<S> predicate, Class<S> type) {
        while (true) {
            if (!console.isFileScanner()) {
                console.writeln("Enter " + name + (limitations == null ? "" : ("; field restrictions: " + limitations)) + " :");
            }
            
            String input = console.read();

            Number val = null;
            if (input != null && !input.isEmpty()) {
                input = input.trim();
                try {
                    if (type == Integer.class) {
                        val = Integer.valueOf(input);
                    } else if (type == Long.class) {
                        val = Long.valueOf(input);
                    } else if (type == Float.class) {
                        val = Float.valueOf(input);
                    } else if (type == Double.class) {
                        val = Double.valueOf(input);
                    }
                } catch (NumberFormatException e) {
                    if (!console.isFileScanner())
                        console.writeln("Incorrect data for " + name + ". Please enter a valid number. Try again.");
                    else
                        return null;
                    continue;
                }
            }

            if (val == null && predicate.test(null)) {
                return null;
            } else if (val == null) {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + ". Value cannot be null. Try again.");
                else
                    return null;
                continue;
            }

            if (predicate.test(type.cast(val))) {
                return type.cast(val);
            }

            if (!console.isFileScanner())
                console.writeln("Incorrect data for " + name + "; make sure that the entered data meets the restrictions: " 
                                + limitations + "\n and try again.");
            else   
                return null;
        
        }
    }

    public String askString(String name, String limitations, Predicate<String> predicate) {

        while (true) {
            if (!console.isFileScanner())
                console.writeln("Enter " + name + (limitations == null ? "" : ("; field restrictions: " + limitations)) + " :");

            String input = console.read();
            if (input != null)
                input = input.trim();

            if (input == "" && predicate.test(""))
                return null;
            
            if (predicate.test(input))
                return input;
            else {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + "; make sure that the entered data meets the restrictions: " + limitations + "\n and try again.");
                else 
                    return null;
            }
        }
    }

    public Boolean askBoolean(String name, String limitations, Predicate<String> predicate) {
        while (true) {
            if (!console.isFileScanner())
                console.writeln("Enter " + name + (limitations == null ? "" : ("; field restrictions: " + limitations)) + " :");
            
            String input = console.read();
            if (input != null) {
                input = input.trim();
            }
            Boolean booleanInput = null;

            if (input == null && predicate.test(null)
                || input.isEmpty() && predicate.test(""))
                return null;
            else if (input == null || input.isEmpty()) {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + ". Value cannot be null. Try again.");
                else
                    return null;
                continue;
            }
            
            if (input.equalsIgnoreCase("TRUE"))
                booleanInput = true;
            else if (input.equalsIgnoreCase("FALSE"))
                booleanInput = false;
            if (booleanInput != null && predicate.test(input))
                return booleanInput;
            else {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + (limitations == null ? "" 
                        : "; make sure that the entered data meets the restrictions: " + limitations + "\n and") + " try again.");
                else
                    return null;
            }
        }
    }

    public LocalDateTime askLocalDateTime(String name, String limitations, Predicate<LocalDateTime> predicate) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            if (!console.isFileScanner())
                console.writeln("Enter the date of " + name + " In the format 'dd-MM-yyyy HH:mm:ss' " + (limitations == null ? "" : ("; field restrictions: " + limitations)) + ":");
            
            String input = console.read();
            if (input != null && input.trim().isEmpty())
                input = input.trim();

            if (input == null && predicate.test(null))
                return null;
            else if (input == null) {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + ". Value cannot be null. Try again.");
                else
                    return null;
                continue;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            try {
                LocalDateTime date = LocalDateTime.parse(input, formatter);
                if (predicate.test(date))
                    return date;
                else {
                    if (!console.isFileScanner())
                        console.writeln("Incorrect data for " + name + "; make sure that the entered data meets the restrictions: " + limitations 
                                        + "\n and matches the format 'dd-MM-yyyy HH:mm:ss' " + "\n and try again.");
                    else
                        return null;
                }
            } catch (DateTimeParseException e) {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + ". Please enter a valid Date. Try again.");
                else
                    return null;
                continue;
            }
        }
    }

    public <E extends Enum<E> > E askEnum(String name, Class<E> enumClass, Predicate<E> predicate) {
        if (!console.isFileScanner())
                console.writeln("Enter " + name + " :");
        E[] availableValues = enumClass.getEnumConstants();
        while (true) {
            if (!console.isFileScanner()) {
                console.writeln("Available values for " + name + ":");

                for (var val : availableValues) {
                    console.writeln("\t" + val.toString() + ';');
                }
            }
            
            String input = console.read();
            if (input != null && !input.trim().isEmpty()) {
                input = input.trim();

                for (var val : availableValues) {
                    if (val.name().equals(input.toUpperCase())) {
                        return val;
                    }
                }
            }

            if ((input == null || input.isEmpty()) && !predicate.test(null)) {
                if (!console.isFileScanner())
                    console.writeln("Incorrect data for " + name + ". Value cannot be null. Try again.");
                else
                    return null;
                continue;
            }
            
            if (!console.isFileScanner())
                console.writeln("The value with name '" + input + "' was not found, check the spelling correctly and try again.");
            else
                return null;
        }
    }
}
