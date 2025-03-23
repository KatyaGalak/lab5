package lab5.collection.ticket;

public enum TicketType {
    VIP(1),
    USUAL(2),
    BUDGETARY(3),
    CHEAP(4);

    private final int value;

    TicketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
