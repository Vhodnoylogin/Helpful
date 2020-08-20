package stream.nodes;

import exceptioned.consumers.ConsumerWithException;
import exceptioned.functions.FunctionWithException;
import exceptioned.functions.FunctionWithException2Params;
import stream.exceptions.StreamEndException;
import stream.exceptions.StreamException;
import stream.helper.CompareFunction;
import stream.helper.StreamDataCollection;
import stream.interfaces.StreamNodeCoalesced;
import stream.interfaces.StreamWithException;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

//public abstract class StreamNode<IN, OUT, S extends StreamWithException<IN> & StreamNodeInterfaceHelper<IN>> implements StreamWithException<OUT>, StreamNodeInterfaceHelper<OUT> {
public abstract class StreamNode<IN, OUT, S extends StreamNodeCoalesced<IN>> implements StreamNodeCoalesced<OUT> {
    protected S prevNode;
    protected List<Exception> errorsList;

    protected void setPrevNode(S prevNode) {
        this.prevNode = prevNode;
    }

    protected void setErrorList(List<Exception> errorsList) {
        this.errorsList = errorsList;
    }


    @Override
    public <L extends Collection<OUT>> L getDataCollection(Supplier<L> genCollection) throws StreamException {
        return this.getData(
                genCollection
                , this.prevNode.getDataCollection(StreamDataCollection.newList())
        );
    }

    protected abstract <LIN extends Collection<IN>, LOUT extends Collection<OUT>>
    LOUT getData(
            Supplier<LOUT> genCollection
            , LIN data
    ) throws StreamException;

    protected void getException(StreamException e) throws StreamException {
        if (!this.errorsList.isEmpty()) {
            this.errorsList.forEach(e::addSuppressed);
            throw e;
        }
        //return e;
    }

    protected void getEndException() throws StreamException {
        this.getException(new StreamEndException());
    }

    @Override
    public final StreamWithException<OUT> filter(FunctionWithException<OUT, Boolean> filter) {
        StreamNodeFilter<OUT> streamNextNode = new StreamNodeFilter<>();
        streamNextNode.setFunc(filter);
        streamNextNode.setPrevNode(this);
        streamNextNode.setErrorList(this.errorsList);
        return streamNextNode;
    }

    @Override
    public final <R> StreamWithException<R> map(FunctionWithException<OUT, R> mapper) {
        StreamNodeMapper<OUT, R> streamNextNode = new StreamNodeMapper<>();
        streamNextNode.setFunc(mapper);
        streamNextNode.setPrevNode(this);
        streamNextNode.setErrorList(this.errorsList);
        return streamNextNode;
    }

    @Override
    public final StreamWithException<OUT> sort(CompareFunction<OUT> sorter) {
        StreamNodeSorter<OUT> streamNextNode = new StreamNodeSorter<>();
        streamNextNode.setFunc(sorter);
        streamNextNode.setPrevNode(this);
        streamNextNode.setErrorList(this.errorsList);
        return streamNextNode;
    }

    @Override
    public StreamWithException<OUT> ifNoExceptions() {
        StreamNodeException<OUT> streamNextNode = new StreamNodeException<>();
        streamNextNode.setPrevNode(this);
        streamNextNode.setErrorList(this.errorsList);
        return streamNextNode;
    }

    @Override
    public StreamWithException<OUT> evaluate() throws StreamException {
        this.getEndException();
        StreamNodeHeader<OUT> streamNextNode = new StreamNodeHeader<>();
        streamNextNode.setData(this.collect(StreamDataCollection.newList()));
        return streamNextNode;
    }

    @Override
    public <LOUT extends Collection<OUT>> LOUT collect(Supplier<LOUT> genCollection) throws StreamException {
        this.getEndException();
        return this.getDataCollection(genCollection);
    }

    @Override
    public <R> R reduce(R base, FunctionWithException2Params<R, OUT, R> reducer) throws StreamException {
        for (OUT out : this.collect(StreamDataCollection.newList())) {
            try {
                base = reducer.accept(base, out);
            } catch (Exception e) {
                this.errorsList.add(e);
            }
        }
        this.getEndException();
        return base;
    }

    @Override
    public void forEach(ConsumerWithException<OUT> each) throws StreamException {
        for (OUT out : this.collect(StreamDataCollection.newList())) {
            try {
                each.accept(out);
            } catch (Exception e) {
                this.errorsList.add(e);
            }
        }
        this.getEndException();
    }
}