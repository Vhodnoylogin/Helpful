package helpfull.stream.interfaces;

import helpfull.exceptioned.consumers.ConsumerWithException;
import helpfull.exceptioned.functions.FunctionWithException;
import helpfull.exceptioned.functions.FunctionWithException2Params;
import helpfull.simplification.CompareFunction;
import helpfull.stream.exceptions.StreamException;
import helpfull.stream.exceptions.StreamInMiddleException;

import java.util.Collection;
import java.util.function.Supplier;

public interface StreamWithException<T> {
    StreamWithException<T> filter(FunctionWithException<T, Boolean> filter);

    <R> StreamWithException<R> map(FunctionWithException<T, R> mapper);

    StreamWithException<T> sort(CompareFunction<T> sorter);

    StreamWithException<T> ifNoExceptions() throws StreamInMiddleException;

    StreamWithException<T> evaluate() throws StreamException;

    // --------------- terminate methods --------------------------------------------
    <R> R reduce(R base, FunctionWithException2Params<R, T, R> reducer) throws StreamException;

    void forEach(ConsumerWithException<T> each) throws StreamException;

    <L extends Collection<T>> L collect(Supplier<L> genCollection) throws StreamException;
}