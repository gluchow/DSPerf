package de.pgl.collection.measure.measurement.set.creator;

public abstract class AbstractSetCreator<T> implements SetCreator<T> {

    protected static void ensureSizeGreaterZero(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
    }

    protected void printCurrentSizeAt100000(int size) {
        if (size > 0 && size % 100000 == 0) { // avoid size 0
            System.out.println("Creating set: " + getImplName() + " - current size: " + size);
        }
    }

}
