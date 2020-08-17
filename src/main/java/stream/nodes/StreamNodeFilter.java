package stream.nodes;

import stream.StreamExceptioned;
import stream.data.StreamDataCollection;

import java.util.Collection;

public class StreamNodeFilter<T> extends StreamNode<T, Boolean, T, StreamExceptioned<T>> {
    @Override
    protected Collection<T> getData(Collection<T> data) {
        Collection<T> out = new StreamDataCollection<>();
        for (T it : data) {
            if (this.accept(it)) {
                out.add(it);
            }
        }
        return out;
    }
}