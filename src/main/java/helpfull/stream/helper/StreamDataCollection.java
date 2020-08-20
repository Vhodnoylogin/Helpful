package helpfull.stream.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class StreamDataCollection<T> extends ArrayList<T> {
    public StreamDataCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public StreamDataCollection() {
    }

    public StreamDataCollection(Collection<? extends T> c) {
        super(c);
    }

    public static <R> Supplier<StreamDataCollection<R>> newList() {
        return StreamDataCollection::new;
    }

//    public static <R, L extends Collection<R>> L newList() {
//        return (L)new StreamDataCollection<>();
//    }
}