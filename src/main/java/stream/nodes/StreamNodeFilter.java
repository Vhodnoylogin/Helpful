package stream.nodes;

import exceptioned.functions.FunctionWithException;
import stream.interfaces.StreamNodeCoalesced;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeFilter<T> extends StreamNode<T, T, StreamNodeCoalesced<T>> {
    protected FunctionWithException<T, Boolean> func;

    protected void setFunc(FunctionWithException<T, Boolean> func) {
        this.func = func;
    }

    @Override
    protected <LIN extends Collection<T>, LOUT extends Collection<T>> LOUT getData(Supplier<LOUT> genCollection, LIN data) {
        LOUT out = genCollection.get();
        for (T it : data) {
            try {
                if (this.func.accept(it)) {
                    out.add(it);
                }
            } catch (Exception e) {
                this.errorsList.add(e);
            }
        }
        return out;
    }
}