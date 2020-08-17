package exceptioned.functions;

@FunctionalInterface
public interface FunctionWithException<T, R> {
    R accept(T data);
}