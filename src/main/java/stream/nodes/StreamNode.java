package stream.nodes;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import stream.StreamExceptioned;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class StreamNode<IN, RES, OUT, S extends StreamExceptioned<IN>>
        implements StreamExceptioned<OUT> {
    protected S prevNode;
    protected FunctionWithException<IN, RES> func;

    protected RES accept(IN data) {
        return this.func.accept(data);
    }

    protected void setPrevNode(S prevNode) {
        this.prevNode = prevNode;
    }

    protected void setFunc(FunctionWithException<IN, RES> func) {
        this.func = func;
    }

    protected abstract Collection<OUT> getData(Collection<IN> data);

    @Override
    public final StreamExceptioned<OUT> filter(FunctionWithException<OUT, Boolean> filter) {
        StreamNodeFilter<OUT> streamFilter = new StreamNodeFilter<>();
        streamFilter.setFunc(filter);
        streamFilter.setPrevNode(this);
        return streamFilter;
    }

    @Override
    public final <R> StreamExceptioned<R> map(FunctionWithException<OUT, R> mapper) {
        StreamNodeMap<OUT, R> streamMap = new StreamNodeMap<>();
        streamMap.setFunc(mapper);
        streamMap.setPrevNode(this);
        return streamMap;
    }

    @Override
    public Collection<OUT> collect(Supplier<OUT> collection) {
        return this.getData(this.prevNode.collect(null));
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