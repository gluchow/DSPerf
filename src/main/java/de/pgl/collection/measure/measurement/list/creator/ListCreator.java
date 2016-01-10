package de.pgl.collection.measure.measurement.list.creator;

import java.util.List;

public interface ListCreator<T> {
    String getImplName();
    List<T> createOrderedList(int size);
    List<T> createRandomList(int size);
}
