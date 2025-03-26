package lab5.io;

/**
 * The IOHandler interface defines methods for reading and writing data of type T.
 * It extends the AutoCloseable interface, allowing implementations to be used in
 * try-with-resources statements for automatic resource management.
 *
 * @param <T> the type of data that this IOHandler will handle
 */
public interface IOHandler<T> extends AutoCloseable {

    /**
     * Reads data of type T.
     *
     * @return the data read from the source
     */
    public T read();

    /**
     * Writes the specified data to the destination.
     *
     * @param data the data to be written
     */
    public void write(T data);

}
