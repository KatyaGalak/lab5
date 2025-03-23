package lab5.collection.ticket;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;

import lab5.configuration.FileConfiguration;
import lab5.file.FileHandler;

public class IDGenerator implements AutoCloseable {
    private static IDGenerator idGenerator = null;
    private final FileHandler fileHandler;
    private final AtomicLong curId = new AtomicLong(0);

    private static final HashSet<Long> idTickets = new HashSet<>();

    public static synchronized IDGenerator getInstance() throws IOException {
        if (idGenerator == null) {
            idGenerator = new IDGenerator();
        }

        return idGenerator;
    }

    private void initialize() {
        try {
            System.out.println("Set curid initial = " );
            String preId = fileHandler.readLastString();
            //System.out.println("Pre_id = " + ((preId == null) ? "null" : preId));
            System.out.println("Set curid initial = " + preId);
            if (preId != null) 
                curId.set(Integer.parseInt(preId));
        } catch (NumberFormatException e) {
            System.out.println("NumberFormat");
            fileHandler.write(Long.toString(curId.getAndSet(0)));
        }
    }

    private IDGenerator() throws IOException {
        System.out.println("yA tut");
        fileHandler = new FileHandler(FileConfiguration.ID_SEQ_PATH, false);
        System.out.println("yA tut");
        initialize();
    }

    public long generateId() throws IOException {
        System.out.println("CUrid = " + curId);
        long newId = curId.incrementAndGet();
        System.out.println("CUrid 2= " + curId);
        fileHandler.write(newId + System.lineSeparator());

        addId(newId);
        return newId;
    }

    public boolean isUnique(long id) {
        return !idTickets.contains(id);
    }

    public void deleteLastID() {
        long delID = curId.decrementAndGet();
        idTickets.remove(delID);
    }

    public void addId(long newId) {
        idTickets.add(newId);
    }

    @Override
    public void close() throws IOException {
        fileHandler.close();
    }
}
