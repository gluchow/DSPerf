package de.pgl.collection.measure.measurement.set.creator;

public abstract class AbstractSetCreator<T> implements SetCreator<T> {

    protected static void ensureSizeGreaterZero(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
    }

}
