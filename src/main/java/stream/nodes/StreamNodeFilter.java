package stream.nodes;

import exceptioned.functions.FunctionWithException;
import stream.StreamWithException;
import stream.data.StreamDataCollection;

import java.util.Collection;

public class StreamNodeFilter<T> extends StreamNode<T, Boolean, T, StreamWithException<T>> {
    protected FunctionWithException<T, Boolean> func;

    protected void setFunc(FunctionWithException<T, Boolean> func) {
        this.func = func;
    }

    @Override
    protected Boolean accept(T data) {
        return this.func.accept(data);
    }

    @Override
    protected Collection<T> getData(Collection<T> data) {
        Collection<T> out = new StreamDataCollection<>();
        for (T it : data) {
            if (this.accept(it)) {
                out.add(it);
            }
        }
        return out;
    }
}