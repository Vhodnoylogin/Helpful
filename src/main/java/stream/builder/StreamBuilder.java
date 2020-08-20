package stream.builder;

import stream.interfaces.StreamWithException;
import stream.nodes.StreamNodeHeader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class StreamBuilder {
    public static <R> StreamWithException<R> collectionToStream(Collection<R> collection) {
        return new StreamNodeHeader<R>().setData(collection);
    }

    public static <R> Supplier<ArrayList<R>> toList() {
        return ArrayList::new;
    }
}