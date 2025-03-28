package lab5.collection;
 
 import java.util.Comparator;
 
 import lab5.collection.ticket.Ticket;
 
 public class TicketComparator implements Comparator<Ticket> {
     @Override
     public int compare(Ticket f, Ticket s) {
         if (f == null)
             return (s == null) ? 0 : -1;
 
         if (s == null) 
             return 1;
 
         return f.compareTo(s);
     }
 }
 