package stream.nodes;

import exceptioned.functions.FunctionWithException2Params;
import stream.StreamWithException;
import stream.data.StreamDataCollection;

import java.util.Collection;

public class StreamNodeSorter<T> extends StreamNode<T, Compare, T, StreamWithException<T>> {
    protected FunctionWithException2Params<T, T, Compare> func;
    protected T baseElement;

    protected void setFunc(FunctionWithException2Params<T, T, Compare> func) {
        this.func = func;
    }

    @Override
    protected Compare accept(T data) {
        return this.func.accept(data, this.baseElement);
    }

    @Override
    protected Collection<T> getData(Collection<T> data) {
        return this.sort(data);
    }

    //  переделать
    // тут какая-то затычка
    protected Collection<T> sort(Collection<T> data) {
        Collection<T> out = new StreamDataCollection<>();
        if (data.isEmpty()) return data;
        for (T datum : data) {
            this.baseElement = datum;
            for (T t : data) {
                T el;
                if (this.accept(el = t) == Compare.LESS) {
                    out.add(el);
                }
            }
        }
        return out;
    }
}