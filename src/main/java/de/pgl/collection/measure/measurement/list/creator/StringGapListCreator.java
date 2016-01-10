package de.pgl.collection.measure.measurement.list.creator;

import org.magicwerk.brownies.collections.GapList;

import java.util.List;
import java.util.Random;

public class StringGapListCreator extends AbstractListCreator<String> {

    @Override
    public String getImplName() {
        return "GapList";
    }

    @Override
    public List<String> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new GapList<>(size);
        for (int i = 0; i < size; i++) {
            result.add("" + i);
        }
        return result;
    }

    @Override
    public List<String> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new GapList<>(size);
        for (int i = 0; i < size; i++) {
            result.add("" + new Random().nextInt());
        }
        return result;
    }

}