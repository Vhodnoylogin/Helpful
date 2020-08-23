package helpful.exceptioned.consumers;

@FunctionalInterface
public interface ConsumerWithException<T> {
    void accept(T data) throws Exception;
}