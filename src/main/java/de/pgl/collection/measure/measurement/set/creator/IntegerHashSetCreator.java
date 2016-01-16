package de.pgl.collection.measure.measurement.set.creator;

import java.util.HashSet;
import java.util.Set;

public class IntegerHashSetCreator extends AbstractSetCreator<Integer> {

    @Override
    public String getImplName() {
        return "HashSet - Integer";
    }

    @Override
    public Set<Integer> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Integer> result = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
            printCurrentSizeAt100000(i);
        }
        return result;
    }
}
