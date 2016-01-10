package de.pgl.collection.measure.measurement.set.creator;

import javolution.util.FastSet;

import java.util.Set;

public class FastSetCreator extends AbstractSetCreator<Integer> {

    @Override
    public String getImplName() {
        return "FastSet";
    }

    @Override
    public Set<Integer> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Integer> result = new FastSet<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }
}
