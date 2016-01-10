package de.pgl.collection.measure.measurement.list.creator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringLinkedListCreator extends AbstractListCreator<String> {

    @Override
    public String getImplName() {
        return "LinkedList";
    }

    @Override
    public List<String> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add("" + i);
        }
        return result;
    }

    @Override
    public List<String> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<String> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add("" + new Random().nextInt());
        }
        return result;
    }

}