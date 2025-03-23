package lab5.collection;

import java.io.IOException;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import lab5.collection.ticket.Ticket;
import lab5.file.CSVHandler;
import lombok.Getter;
import lab5.configuration.FileConfiguration;

@Getter
public class CollectionManager {
    private static CollectionManager ticketCollectionSingleton;
    private TreeSet<Ticket> ticketCollection = new TreeSet<>(new TicketComparator());

    @Getter
    private LocalDateTime creationDate;

    public static CollectionManager getInstance() {
        return ticketCollectionSingleton == null ? ticketCollectionSingleton = new CollectionManager() : ticketCollectionSingleton;
    }

    private CollectionManager() {
        loadCollection();
        creationDate = LocalDateTime.now();
    }

    public void setCollection(TreeSet<Ticket> collection) {
        this.ticketCollection = collection;
    }

    public void loadCollection() {
        ticketCollection.clear();
        
        try (CSVHandler cvsHandler = new CSVHandler(FileConfiguration.FILE_PATH, true)) {
            ticketCollection.addAll(cvsHandler.read());
        } catch (IOException e) {
            System.err.println("[Error] Error loading the collection. " + e.getMessage());
        }
    }

    public void saveCollection() {
        try (CSVHandler cvsHandler = new CSVHandler(FileConfiguration.FILE_PATH, false)) {
            cvsHandler.write(ticketCollection);
        } catch (IOException e) {
            System.err.println("[Error] Error saving the collection. " + e.getMessage());
        }
    }

    public List<Ticket> getList() {
        return new ArrayList<>(ticketCollection);
    }
}
