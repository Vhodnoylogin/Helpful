package helpfull.exceptioned.functions;

@FunctionalInterface
public interface FunctionWithException0Params<R> {
    R accept() throws Exception;
}