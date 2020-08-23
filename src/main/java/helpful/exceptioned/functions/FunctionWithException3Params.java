package helpful.exceptioned.functions;

@FunctionalInterface
public interface FunctionWithException3Params<T1, T2, T3, R> {
    R accept(T1 param1, T2 param2, T3 param3) throws Exception;
}