package de.pgl.collection.measure.measurement.list.creator;

import org.magicwerk.brownies.collections.BigList;

import java.util.List;
import java.util.Random;

public class StringBigListCreator extends AbstractListCreator<String> {

    @Override
    public String getImplName() {
        return "BigList";
    }

    @Override
    public List<String> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new BigList<>(size);
        for (int i = 0; i < size; i++) {
            result.add("" + i);
        }
        return result;
    }

    @Override
    public List<String> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new BigList<>(size);
        for (int i = 0; i < size; i++) {
            result.add("" + new Random().nextInt());
        }
        return result;
    }

}