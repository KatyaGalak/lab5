package lab5.collection.ticket;

public enum TicketType {
    VIP(4),
    USUAL(3),
    BUDGETARY(2),
    CHEAP(1);

    private final int value;

    TicketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
