package stream.nodes;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import stream.StreamWithException;
import stream.helper.Compare;
import stream.helper.StreamDataCollection;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public abstract class StreamNode<IN, OUT, S extends StreamWithException<IN>>
        implements StreamWithException<OUT> {
    protected S prevNode;
    protected List<Exception> errorsList;

    protected void setPrevNode(S prevNode) {
        this.prevNode = prevNode;
    }

    protected void listErrorList(List<Exception> errorsList) {
        this.errorsList = errorsList;
    }

    protected static <R> Collection<R> getNewCollection(Supplier<Collection<R>> collection) {
        return (null == collection ? StreamDataCollection.<R>newList() : collection).get();
    }

    protected abstract Collection<OUT> getData(
            Supplier<Collection<OUT>> collection
            , Collection<IN> data
    ) throws Exception;

    @Override
    public final StreamWithException<OUT> filter(FunctionWithException<OUT, Boolean> filter) {
        StreamNodeFilter<OUT> streamFilter = new StreamNodeFilter<>();
        streamFilter.setFunc(filter);
        streamFilter.setPrevNode(this);
        streamFilter.listErrorList(this.errorsList);
        return streamFilter;
    }

    @Override
    public final <R> StreamWithException<R> map(FunctionWithException<OUT, R> mapper) {
        StreamNodeMapper<OUT, R> streamMap = new StreamNodeMapper<>();
        streamMap.setFunc(mapper);
        streamMap.setPrevNode(this);
        streamMap.listErrorList(this.errorsList);
        return streamMap;
    }

    @Override
    public final StreamWithException<OUT> sort(FunctionWithException2Params<OUT, OUT, Compare> sorter) {
        StreamNodeSorter<OUT> streamSort = new StreamNodeSorter<>();
        streamSort.setFunc(sorter);
        streamSort.setPrevNode(this);
        streamSort.listErrorList(this.errorsList);
        return streamSort;
    }

    @Override
    public StreamWithException<OUT> evaluate() throws Exception {
        try {
            StreamNodeHeader<OUT> streamHead = new StreamNodeHeader<>();
            streamHead.setData(this.collect(null));
            streamHead.listErrorList(this.errorsList);
            return streamHead;
        } catch (Exception e) {
            this.errorsList.forEach(e::addSuppressed);
            throw e;
        }

    }

    @Override
    public Collection<OUT> collect(Supplier<Collection<OUT>> collection) throws Exception {
        try {
            Collection<OUT> out = getNewCollection(collection);
            out.addAll(this.getData(
                    collection
                    , this.prevNode.collect(null)
            ));
            return out;
        } catch (Exception e) {
            this.errorsList.forEach(e::addSuppressed);
            throw e;
        }
    }

    @Override
    public <R> R reduce(R base, FunctionWithException2Params<R, OUT, R> reducer) throws Exception {
        try {
            for (OUT out : this.collect(null)) {
                base = reducer.accept(base, out);
            }
            return base;
        } catch (Exception e) {
            this.errorsList.forEach(e::addSuppressed);
            throw e;
        }
    }

    @Override
    public void forEach(ConsumerWithException<OUT> each) throws Exception {
        try {
            for (OUT out : this.collect(null)) {
                each.accept(out);
            }
        } catch (Exception e) {
            this.errorsList.forEach(e::addSuppressed);
            throw e;
        }
    }

    //protected void
}