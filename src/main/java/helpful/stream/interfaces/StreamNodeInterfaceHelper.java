package helpful.stream.interfaces;

import helpful.stream.exceptions.StreamException;

import java.util.Collection;
import java.util.function.Supplier;

public interface StreamNodeInterfaceHelper<T> {
    <LOUT extends Collection<T>> LOUT getDataCollection(Supplier<LOUT> genCollection) throws StreamException;
}