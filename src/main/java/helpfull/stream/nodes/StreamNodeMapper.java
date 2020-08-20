package helpfull.stream.nodes;

import helpfull.exceptioned.functions.FunctionWithException;
import helpfull.stream.interfaces.StreamNodeCoalesced;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeMapper<T, R> extends StreamNode<T, R, StreamNodeCoalesced<T>> {
    protected FunctionWithException<T, R> func;

    protected void setFunc(FunctionWithException<T, R> func) {
        this.func = func;
    }

    @Override
    protected <LIN extends Collection<T>, LOUT extends Collection<R>> LOUT getData(Supplier<LOUT> genCollection, LIN data) {
        LOUT out = genCollection.get();
        for (T it : data) {
            try {
                out.add(this.func.accept(it));
            } catch (Exception e) {
                this.errorsList.add(e);
            }
        }
        return out;
    }
}