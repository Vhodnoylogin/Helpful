package helpful.exceptioned.consumers;

@FunctionalInterface
public interface ConsumerWithException3Params<T1, T2, T3> {
    void accept(T1 param1, T2 param2, T3 param3) throws Exception;
}