package de.pgl.collection.measure.measurement.set.creator;

import java.util.Set;
import java.util.TreeSet;

public class IntegerTreeSetCreator extends AbstractSetCreator<Integer> {

    @Override
    public String getImplName() {
        return "TreeSet - Integer";
    }

    @Override
    public Set<Integer> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
            printCurrentSizeAt100000(i);
        }
        return result;
    }
}
