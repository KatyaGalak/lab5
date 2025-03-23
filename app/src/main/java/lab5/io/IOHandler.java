package lab5.io;

public interface IOHandler<T> extends AutoCloseable {
    public T read();
    public void write(T data);
}