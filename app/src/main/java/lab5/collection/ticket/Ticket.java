package lab5.collection.ticket;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.NonNull;
import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@JsonPropertyOrder({"name", "coordinates", "price", "refundable", "type", "person"})
public class Ticket implements Comparable<Ticket> {
    
    @NonNull
    private String name; // Поле не может быть null, Строка не может быть пустой
    
    @JsonUnwrapped
    @NonNull
    private Coordinates coordinates; // Поле не может быть null
    
    @JsonIgnore
    @NonNull
    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    
    private double price; // Значение поля должно быть больше 0
    
    private boolean refundable;

    public boolean getRefundable() {
        return refundable;
    }
    
    @NonNull
    private TicketType type; // Поле не может быть null
    
    @JsonUnwrapped
    @NonNull
    private Person person; // Поле не может быть null

    @Setter(AccessLevel.NONE)
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    
    @JsonIgnore
    private Boolean installedPrice = false;
    @JsonIgnore
    private Boolean installedRefundable = false;

    {
        try {
            id = IDGenerator.getInstance().generateId();
            System.out.println("IDDDD = " + id);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            id = -1;
        }
    }

    public Ticket() {
        this.creationDate = LocalDateTime.now();
    }

    public void validate() throws IllegalArgumentException {
        if (id <= 0) throw new IllegalArgumentException("ID must be >= 0");

        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

        if (installedPrice && price <= 0) throw new IllegalArgumentException("Price must be >= 0");

        if (coordinates == null) throw new IllegalArgumentException("Coordinates cannot be null");

        if (type == null) throw new IllegalArgumentException("Type cannot be null");

        if (person == null) throw new IllegalArgumentException("Person cannot be null");

        person.validate();
    }

    public Ticket(String name, Coordinates coordinates, 
                    Double price, Boolean refundable, TicketType type, Person person) {
        this();
        this.name = name;
        this.coordinates = coordinates;

        if (price != null)
            installedPrice = true;
        this.price = (price == null ? Integer.MIN_VALUE : price);
        
        if (refundable != null)
            installedRefundable = true;
        this.refundable = (refundable == null ? false : refundable);

        this.type = type;
        this.person = person;

        validate();
    }

    /*public void setId(long id) {
        try {
            if (id < 0)
                throw new IllegalArgumentException("ID must be >= 0");
            if (this.id != id && !IDGenerator.getInstance().isUnique(id)) {
                throw new IllegalArgumentException("ID must be unique");
            }
    
            IDGenerator.getInstance().addId(id);
            IDGenerator.getInstance().removeId(this.id);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        

        this.id = id;
    }*/

    @Override
    public int compareTo(Ticket ticket) {
        if (ticket == null) return 1;

        int ans = Double.compare(price, ticket.price);

        if (ans == 0) ans = this.creationDate.compareTo(ticket.creationDate);

        if (ans == 0) ans = this.coordinates.compareTo(ticket.coordinates);
        
        if (ans == 0) ans = this.person.compareTo(ticket.person);

        // TO DO

        return ans;
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "\n\t id = " + id +
                "\n\t name = " + name +
                "\n\t" + coordinates +
                "\n\t creationData = " + creationDate +
                (installedPrice ? "\n\t price = " + price : "") +
                (installedRefundable ? "\n\t refundable = " + refundable : "") +
                "\n\t type = " + type +
                "\n\t" + person + "\n}";
    }

}
