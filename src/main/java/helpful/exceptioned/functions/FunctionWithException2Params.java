package helpful.exceptioned.functions;

@FunctionalInterface
public interface FunctionWithException2Params<T1, T2, R> {
    R accept(T1 param1, T2 param2) throws Exception;
}