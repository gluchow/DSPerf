package de.pgl.collection.measure.measurement.list.creator;

import org.magicwerk.brownies.collections.GapList;

import java.util.List;
import java.util.Random;

public class GapListCreator extends AbstractListCreator<Integer> {

    @Override
    public String getImplName() {
        return "GapList - Integer";
    }

    @Override
    public List<Integer> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new GapList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
            printCurrentSizeAt100000(i);
        }
        return result;
    }

    @Override
    public List<Integer> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new GapList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Random().nextInt(size));
            printCurrentSizeAt100000(i);
        }
        return result;
    }

}