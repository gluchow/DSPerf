package de.pgl.collection.measure.measurement.list;

public abstract class AbstractListCreator<T> implements ListCreator<T> {

    protected static void ensureSizeGreaterZero(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
    }

}
