package de.pgl.collection.measure.measurement.set.creator;

import java.util.Set;

public interface SetCreator<T> {
    String getImplName();

    Set<T> createSet(int size);
}
