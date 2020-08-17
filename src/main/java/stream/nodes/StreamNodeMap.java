package stream.nodes;

import stream.StreamExceptioned;
import stream.data.StreamDataCollection;

import java.util.Collection;

public class StreamNodeMap<T, R> extends StreamNode<T, R, R, StreamExceptioned<T>> {
    @Override
    protected Collection<R> getData(Collection<T> data) {
        Collection<R> out = new StreamDataCollection<>();
        for (T it : data) {
            out.add(this.accept(it));
        }
        return out;
    }
}