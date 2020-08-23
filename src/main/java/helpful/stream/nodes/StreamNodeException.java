package helpful.stream.nodes;

import helpful.stream.exceptions.StreamException;
import helpful.stream.exceptions.StreamInMiddleException;
import helpful.stream.interfaces.StreamNodeCoalesced;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeException<T> extends StreamNode<T, T, StreamNodeCoalesced<T>> {
    @Override
    protected <LIN extends Collection<T>, LOUT extends Collection<T>> LOUT getData(Supplier<LOUT> genCollection, LIN data) throws StreamException {
        this.getException(new StreamInMiddleException());
        LOUT out = genCollection.get();
        out.addAll(data);
        return out;
    }
}