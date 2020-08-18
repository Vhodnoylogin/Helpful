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

    StreamWithException<T> evaluate() throws Exception;

    <R> R reduce(R base, FunctionWithException2Params<R, T, R> reducer) throws Exception;

    void forEach(ConsumerWithException<T> each) throws Exception;

    Collection<T> collect(Supplier<Collection<T>> collection) throws Exception;
}