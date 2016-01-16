package de.pgl.collection.measure.measurement.list.creator;

public abstract class AbstractListCreator<T> implements ListCreator<T> {

    protected static void ensureSizeGreaterZero(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
    }

    protected void printCurrentSizeAt100000(int size) {
        if (size % 100000 == 0) {
            System.out.println("Creating list: " + getImplName() + " - current size: " + size);
        }
    }

}
