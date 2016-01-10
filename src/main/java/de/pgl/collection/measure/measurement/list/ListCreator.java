package de.pgl.collection.measure.measurement.list;

import java.util.List;

public interface ListCreator<T> {
    List<T> createOrderedList(int size);
    List<T> createRandomList(int size);
}
