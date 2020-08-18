package stream.nodes;

import exceptioned.functions.FunctionWithException;
import stream.StreamWithException;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeFilter<T> extends StreamNode<T, T, StreamWithException<T>> {
    protected FunctionWithException<T, Boolean> func;

    protected void setFunc(FunctionWithException<T, Boolean> func) {
        this.func = func;
    }

    @Override
    protected Collection<T> getData(Supplier<Collection<T>> collection, Collection<T> data) {
        Collection<T> out = getNewCollection(collection);
        for (T it : data) {
            if (this.func.accept(it)) {
                out.add(it);
            }
        }
        return out;
    }
}