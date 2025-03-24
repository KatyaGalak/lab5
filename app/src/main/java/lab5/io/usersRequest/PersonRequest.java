package lab5.io.usersRequest;

import lab5.io.console.Console;
import lab5.collection.ticket.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.function.Predicate;

import lab5.collection.ticket.Color;

public class PersonRequest extends Request<Person> {
    private static HashSet<String> passportIDPerson = new HashSet<>();

    public static boolean checkUnique(String passportID) {
        if (!passportIDPerson.contains(passportID)) {
            passportIDPerson.add(passportID);
            return true;
        }

        return false;
    }

    public static void deletePassportID(String passportID) {
        if (passportIDPerson.contains(passportID)) {
            passportIDPerson.remove(passportID);
        }
    }

    public PersonRequest(Console console) {
        super(console);
    }

    @Override
    public Person create() {
        Predicate<LocalDateTime> predicateLocalDataTime = x -> (x != null);
        LocalDateTime dataTime = askLocalDateTime("Person's birthday", "The value cannot be empty", predicateLocalDataTime);

        Predicate<String> predicatePassportID = x -> (x != null && x.length() >= 5 && x.length() <= 43 && (!passportIDPerson.contains(x)));
        String passportId = askString("Person's Passport ID", "The ID must be unique, with a min length of 5 and a max length of 43", predicatePassportID);

        Predicate<Color> predicateColor = x -> (x != null);
        Color color = askColor();

        if (!predicatePassportID.test(passportId))
            return null;

        if (!predicateLocalDataTime.test(dataTime))
            return null;

        if (!predicateColor.test(color)) 
            return null;

        Person person = new Person(dataTime, passportId, color);

        return person;
    }

    private Color askColor() {
        Predicate<Color> predicateColor = x -> (x != null);
        Color color = askEnum("Person hair color", Color.class, predicateColor);

        if (!predicateColor.test(color))
            return null;

        return color;
    }
}
