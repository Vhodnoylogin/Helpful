package stream.nodes;

import exceptioned.functions.FunctionWithException;
import stream.StreamExceptioned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeHead<T> extends StreamNode<T, T, T, StreamExceptioned<T>> {
    protected Collection<T> data;

    public StreamNodeHead() {
        this.func = (x) -> x;
    }

    public StreamNodeHead<T> setData(Collection<T> data) {
        this.data = data;
        return this;
    }

    protected void setFunc(FunctionWithException<T, T> func) {
    }

    @Override
    protected Collection<T> getData(Collection<T> data) {
        return data;
    }

    @Override
    public Collection<T> collect(Supplier<T> collection) {
        ArrayList<T> out = new ArrayList<>(this.data);
        return this.getData(out);
    }
}