package stream;

import stream.nodes.StreamNodeHead;

import java.util.Collection;

public class StreamBuilder {
    public static <R> StreamExceptioned<R> collectionToStream(Collection<R> collection) {
        return new StreamNodeHead<R>().setData(collection);
    }
}