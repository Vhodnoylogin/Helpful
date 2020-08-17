package stream.nodes;

import stream.StreamWithException;
import stream.data.StreamDataCollection;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeHead<T> extends StreamNode<T, T, T, StreamWithException<T>> {
    protected Collection<T> data;

//    public StreamNodeHead() {
//        this.func = (x) -> x;
//    }

    public StreamNodeHead<T> setData(Collection<T> data) {
        this.data = data;
        return this;
    }

//    protected void setFunc(FunctionWithException<T, T> func) {
//    }

    @Override
    protected T accept(T data) {
        return data;
    }

    @Override
    protected Collection<T> getData(Collection<T> data) {
        return data;
    }

    @Override
    public Collection<T> collect(Supplier<T> collection) {
        StreamDataCollection<T> out = new StreamDataCollection<>(this.data);
        return this.getData(out);
    }
}