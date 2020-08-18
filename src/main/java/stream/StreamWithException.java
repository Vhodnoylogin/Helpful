package stream;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import stream.helper.Compare;

import java.util.Collection;
import java.util.function.Supplier;

public interface StreamWithException<T> {
    StreamWithException<T> filter(FunctionWithException<T, Boolean> filter);

    <R> StreamWithException<R> map(FunctionWithException<T, R> mapper);

    StreamWithException<T> sort(FunctionWithException2Params<T, T, Compare> sorter);

    <R> R reduce(R base, FunctionWithException2Params<R, T, R> reducer);

    void forEach(ConsumerWithException<T> each);

    //<R extends Collection<T>> R collect(Supplier<R> collection);
    Collection<T> collect(Supplier<Collection<T>> collection);
}