package helpful.exceptioned.consumers;

@FunctionalInterface
public interface ConsumerWithException2Params<T1, T2> {
    void accept(T1 param1, T2 param2) throws Exception;
}