package stream.nodes;

import stream.StreamWithException;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeHead<T> extends StreamNode<T, T, StreamWithException<T>> {
    protected Collection<T> data;

    public StreamNodeHead<T> setData(Collection<T> data) {
        this.data = data;
        return this;
    }

    @Override
    protected Collection<T> getData(Supplier<Collection<T>> collection, Collection<T> data) {
        return data;
    }

    @Override
    public Collection<T> collect(Supplier<Collection<T>> collection) {
        Collection<T> out = getNewCollection(collection);
        out.addAll(this.getData(collection, this.data));
        return out;
    }
}