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

/**
 * The CollectionManager class manages a collection of Ticket objects.
 * It provides methods to load and save the collection from/to a CSV file,
 * as well as access the collection as a list.
 */
@Getter
public class CollectionManager {
    
    /**
     * Retrieves the singleton instance of the CollectionManager.
     * 
     * @return the singleton instance of CollectionManager
     */

    private static CollectionManager ticketCollectionSingleton;
    private TreeSet<Ticket> ticketCollection = new TreeSet<>();

    @Getter
    private LocalDateTime creationDate;

    public static CollectionManager getInstance() { 

        return ticketCollectionSingleton == null ? ticketCollectionSingleton = new CollectionManager() : ticketCollectionSingleton;
    }

    private CollectionManager() {
        loadCollection();
        creationDate = LocalDateTime.now();
    }

    /**
     * Sets the ticket collection to the specified TreeSet of Tickets.
     * 
     * @param collection the TreeSet of Tickets to set
     */
    public void setCollection(TreeSet<Ticket> collection) {

        this.ticketCollection = collection;
    }

    /**
     * Loads the collection of Tickets from a CSV file.
     * Clears the existing collection before loading.
     */
    public void loadCollection() {

        ticketCollection.clear();
        
        try (CSVHandler cvsHandler = new CSVHandler(FileConfiguration.FILE_PATH, true)) {
            ticketCollection.addAll(cvsHandler.read());
        } catch (IOException e) {
            System.err.println("[Error] Error loading the collection. " + e.getMessage());
        }
    }

    /**
     * Saves the current collection of Tickets to a CSV file.
     */
    public void saveCollection() {

        try (CSVHandler cvsHandler = new CSVHandler(FileConfiguration.FILE_PATH, false)) {
            cvsHandler.write(ticketCollection);
        } catch (IOException e) {
            System.err.println("[Error] Error saving the collection. " + e.getMessage());
        }
    }

    /**
     * Returns a list representation of the current ticket collection.
     * 
     * @return a List of Tickets
     */
    public List<Ticket> getList() {

        return new ArrayList<>(ticketCollection);
    }
}
