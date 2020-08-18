package stream.builder;

import stream.StreamWithException;
import stream.nodes.StreamNodeHead;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class StreamBuilder {
    public static <R> StreamWithException<R> collectionToStream(Collection<R> collection) {
        return new StreamNodeHead<R>().setData(collection);
    }

    public static <R> Supplier<ArrayList<R>> toList() {
        return ArrayList::new;
    }
}