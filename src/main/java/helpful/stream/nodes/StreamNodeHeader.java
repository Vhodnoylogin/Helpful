package helpful.stream.nodes;

import helpful.stream.helper.StreamDataCollection;
import helpful.stream.interfaces.StreamNodeCoalesced;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeHeader<T> extends StreamNode<T, T, StreamNodeCoalesced<T>> {
    protected Collection<T> data;

    public StreamNodeHeader<T> setData(Collection<T> data) {
        this.data = data;
        this.errorsList = new StreamDataCollection<>();
        return this;
    }

    @Override
    protected <LIN extends Collection<T>, LOUT extends Collection<T>> LOUT getData(Supplier<LOUT> genCollection, LIN data) {
        LOUT out = genCollection.get();
        out.addAll(data);
        return out;
    }

    @Override
    public <LOUT extends Collection<T>> LOUT getDataCollection(Supplier<LOUT> genCollection) {
        return this.getData(genCollection, this.data);
    }
}