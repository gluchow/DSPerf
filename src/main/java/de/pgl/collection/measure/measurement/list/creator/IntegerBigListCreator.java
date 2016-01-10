package de.pgl.collection.measure.measurement.list.creator;

import org.magicwerk.brownies.collections.BigList;

import java.util.List;
import java.util.Random;

public class IntegerBigListCreator extends AbstractListCreator<Integer> {

    @Override
    public String getImplName() {
        return "BigList";
    }

    @Override
    public List<Integer> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new BigList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    @Override
    public List<Integer> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new BigList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Random().nextInt());
        }
        return result;
    }

}