package lab5.collection.ticket;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import lab5.io.usersRequest.PersonRequest;

@JsonPropertyOrder({"birthday", "passportID", "hairColor"})
public class Person implements Comparable<Person> {
    @JsonProperty("birthday")
    private LocalDateTime birthday; // Поле не может быть null

    @JsonProperty("passportID")
    private String passportID; // Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 5, Длина строки не должна быть больше 43, Поле не может быть null
    
    @JsonProperty("hairColor")
    private Color hairColor; // Поле не может быть null

    public Person(LocalDateTime birthday, String passportID, Color haiColor) {
        this.birthday = birthday;
        this.passportID = passportID;
        this.hairColor = haiColor;
    }

    public void validate() throws IllegalArgumentException {
        if (birthday == null) throw new IllegalArgumentException("Birthday cannot be null");

        if (hairColor == null) throw new IllegalArgumentException("HairColor cannot be null");

        if (passportID == null) throw new IllegalArgumentException("PassportID cannot be null");

        if (passportID.length() < 5 || passportID.length() > 43) throw new IllegalArgumentException("The length of the passportID does not comply with the rules");

        if (!PersonRequest.checkUnique(passportID)) throw new IllegalArgumentException("PassportID must be unique");

    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getPassportID() {
        return passportID;
    }

    public Color getHairColor() {
        return hairColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday, passportID, hairColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return true;

        Person person = (Person) o;

        return Objects.equals(hairColor, person.hairColor) &&
                Objects.equals(passportID, person.passportID) &&
                Objects.equals(birthday, person.birthday);
    }

    @Override
    public String toString() {
        String ans = "Person: [";

        ans += "birthday = " + birthday.toString()
                + ", passportID = " + passportID
                + ", hairColor = " + hairColor.toString()
                + "]\n";

        return ans;
    }

    @Override
    public int compareTo(Person person) {
        if (person == null) return 1;

        int ans = this.birthday.compareTo(person.birthday);

        if (ans == 0) ans = this.passportID.compareTo(person.passportID);

        if (ans == 0) ans = this.hairColor.compareTo(person.hairColor);

        return ans;
    }
}
