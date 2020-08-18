package stream.nodes;

import exceptioned.functions.FunctionWithException2Params;
import stream.StreamWithException;
import stream.helper.Compare;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeSorter<T> extends StreamNode<T, T, StreamWithException<T>> {
    protected FunctionWithException2Params<T, T, Compare> func;

    protected void setFunc(FunctionWithException2Params<T, T, Compare> func) {
        this.func = func;
    }

    @Override
    protected Collection<T> getData(Supplier<Collection<T>> collection, Collection<T> data) {
        return this.sort(collection, data);
    }


    //  переделать
    // тут какая-то затычка
    protected Collection<T> sort(Supplier<Collection<T>> collection, Collection<T> data) {
        Collection<T> out = getNewCollection(collection);
        if (data.isEmpty()) return data;
        for (T datum : data) {
            for (T t : data) {
                try {
                    if (this.func.accept(datum, t) == Compare.LESS) {
                        out.add(t);
                    }
                } catch (Exception e) {
                    this.errorsList.add(e);
                }
            }
        }
        return out;
    }
}