package exceptioned.consumers;

@FunctionalInterface
public interface ConsumerWithException<T> {
    void accept(T data);
}