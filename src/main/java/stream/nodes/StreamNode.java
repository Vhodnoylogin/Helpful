package stream.nodes;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import stream.StreamWithException;
import stream.helper.Compare;
import stream.helper.StreamDataCollection;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class StreamNode<IN, OUT, S extends StreamWithException<IN>>
        implements StreamWithException<OUT> {
    protected S prevNode;

    protected void setPrevNode(S prevNode) {
        this.prevNode = prevNode;
    }

    protected static <R> Collection<R> getNewCollection(Supplier<Collection<R>> collection) {
        return (null == collection ? StreamDataCollection.<R>newList() : collection).get();
    }

    protected abstract Collection<OUT> getData(Supplier<Collection<OUT>> collection, Collection<IN> data);

    @Override
    public final StreamWithException<OUT> filter(FunctionWithException<OUT, Boolean> filter) {
        StreamNodeFilter<OUT> streamFilter = new StreamNodeFilter<>();
        streamFilter.setFunc(filter);
        streamFilter.setPrevNode(this);
        return streamFilter;
    }

    @Override
    public final <R> StreamWithException<R> map(FunctionWithException<OUT, R> mapper) {
        StreamNodeMapper<OUT, R> streamMap = new StreamNodeMapper<>();
        streamMap.setFunc(mapper);
        streamMap.setPrevNode(this);
        return streamMap;
    }

    @Override
    public final StreamWithException<OUT> sort(FunctionWithException2Params<OUT, OUT, Compare> sorter) {
        StreamNodeSorter<OUT> streamSort = new StreamNodeSorter<>();
        streamSort.setFunc(sorter);
        streamSort.setPrevNode(this);
        return streamSort;
    }

    @Override
    public Collection<OUT> collect(Supplier<Collection<OUT>> collection) {
        Collection<OUT> out = getNewCollection(collection);
        out.addAll(this.getData(
                collection
                , this.prevNode.collect(null)
        ));
        return out;
    }

    @Override
    public <R> R reduce(R base, FunctionWithException2Params<R, OUT, R> reducer) {
        for (OUT out : this.collect(null)) {
            base = reducer.accept(base, out);
        }
        return base;
    }

    @Override
    public void forEach(ConsumerWithException<OUT> each) {
        for (OUT out : this.collect(null)) {
            each.accept(out);
        }
    }
}