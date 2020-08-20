package stream.nodes;

import stream.helper.CompareFunction;
import stream.interfaces.StreamNodeCoalesced;

import java.util.Collection;
import java.util.function.Supplier;

public class StreamNodeSorter<T> extends StreamNode<T, T, StreamNodeCoalesced<T>> {
    protected CompareFunction<T> func;

    protected void setFunc(CompareFunction<T> func) {
        this.func = func;
    }

    @Override
    protected <LIN extends Collection<T>, LOUT extends Collection<T>> LOUT getData(Supplier<LOUT> genCollection, LIN data) {
        return this.sort(genCollection, data);
    }

    //  переделать
    // тут какая-то затычка
    protected <LIN extends Collection<T>, LOUT extends Collection<T>> LOUT sort(Supplier<LOUT> genCollection, LIN data) {
        LOUT out = genCollection.get();
        if (data.isEmpty()) return out;
        for (T datum : data) {
            for (T t : data) {
                try {
                    if (this.func.accept(datum, t) == CompareFunction.Compare.LESS) {
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