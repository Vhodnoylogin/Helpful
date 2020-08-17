package stream.data;

import java.util.ArrayList;
import java.util.Collection;

public class StreamDataCollection<T> extends ArrayList<T> {
    public StreamDataCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public StreamDataCollection() {
    }

    public StreamDataCollection(Collection<? extends T> c) {
        super(c);
    }
}