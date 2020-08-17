package stream.nodes;

import exceptioned.functions.FunctionWithException;
import stream.StreamWithException;
import stream.data.StreamDataCollection;

import java.util.Collection;

public class StreamNodeMapper<T, R> extends StreamNode<T, R, R, StreamWithException<T>> {
    protected FunctionWithException<T, R> func;

    protected void setFunc(FunctionWithException<T, R> func) {
        this.func = func;
    }

    @Override
    protected R accept(T data) {
        return this.func.accept(data);
    }

    @Override
    protected Collection<R> getData(Collection<T> data) {
        Collection<R> out = new StreamDataCollection<>();
        for (T it : data) {
            out.add(this.accept(it));
        }
        return out;
    }
}