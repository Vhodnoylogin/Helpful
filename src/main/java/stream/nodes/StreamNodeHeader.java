package stream.nodes;

import stream.StreamWithException;
import stream.helper.StreamDataCollection;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeHeader<T> extends StreamNode<T, T, StreamWithException<T>> {
    protected Collection<T> data;

    public StreamNodeHeader<T> setData(Collection<T> data) {
        this.data = data;
        this.errorsList = new StreamDataCollection<>();
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