package helpful.exceptioned.functions;

@FunctionalInterface
public interface FunctionWithException0Params<R> {
    R accept() throws Exception;
}