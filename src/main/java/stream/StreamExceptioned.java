package stream;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;

import java.util.Collection;
import java.util.function.Supplier;

public interface StreamExceptioned<T> {
    //StreamExceptioned<T> setCollection(Collection<T> collection);
    StreamExceptioned<T> filter(FunctionWithException<T, Boolean> filter);

    <R> StreamExceptioned<R> map(FunctionWithException<T, R> mapper);

    <R> R reduce(R base, FunctionWithException2Params<R, T, R> reducer);

    void forEach(ConsumerWithException<T> each);

    Collection<T> collect(Supplier<T> collection);
}