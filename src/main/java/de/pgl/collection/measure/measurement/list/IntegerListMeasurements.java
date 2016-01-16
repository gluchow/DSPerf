package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.*;

import java.util.*;

public class IntegerListMeasurements {
    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public IntegerListMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new IntegerArrayListCreator());
            proceedListOperations(new IntegerLinkedListCreator());
            proceedListOperations(new IntegerGapListCreator());
            proceedListOperations(new IntegerBigListCreator());
            proceedListOperations(new IntegerTreeListCreator());
            proceedListOperations(new IntegerFastTableCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getDurations());
    }

    private void proceedListOperations(ListCreator<Integer> listCreator) {
        String implName = listCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<Integer> list = listCreator.createOrderedList(size);

        measurementsHolder.addMeasure(new Measurement(implName, "find_first", size,
                () -> list.indexOf(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_center", size,
                () -> list.indexOf(center())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_last", size,
                () -> list.indexOf(last())));

        measurementsHolder.addMeasure(new Measurement(implName, "get_first", size,
                () -> list.get(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_center", size,
                () -> list.get(center())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_last", size,
                () -> list.get(last())));

        measurementsHolder.addMeasure(new Measurement(implName, "add_first", size,
                () -> list.add(last())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_center", size,
                () -> list.add(center(), last())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_last", size,
                () -> list.add(first(), last())));

        measurementsHolder.addMeasure(new Measurement(implName, "remove_first", size,
                () -> list.remove(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_center", size,
                () -> list.remove(center())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_last", size,
                () -> list.remove(last())));


        measurementsHolder.addMeasure(new Measurement(implName, "size", size,
                list::size));

        measurementsHolder.addMeasure(new Measurement(implName, "replace at center", size,
                () -> list.set(center(), 222)));

        measurementsHolder.addMeasure(new Measurement(implName, "create sublist of 500 elements by index", size,
                () -> list.subList(size / 4, size / 4 + 500)));


        // Tests with other lists
        List<Integer> randomListToSort = listCreator.createRandomList(size);
        measurementsHolder.addMeasure(new Measurement(implName, "sort a random list", size,
                () -> Collections.sort(randomListToSort)));

        List<Integer> orderedList = listCreator.createOrderedList(500);
        measurementsHolder.addMeasure(new Measurement(implName, "contains ordered list of 500 elements", size,
                () -> list.containsAll(orderedList)));

        List<Integer> randomList = listCreator.createRandomList(size).subList(size / 2, size / 2 + 500);
        measurementsHolder.addMeasure(new Measurement(implName, "contains random list of max 500 elements", size,
                () -> list.containsAll(randomList)));


        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - last position", size,
                () -> list.addAll(orderedList)));
        // reset:
        removeLast500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of 500 max elements - last position", size,
                () -> list.addAll(randomList)));
        // reset:
        removeLast500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - first position", size,
                () -> list.addAll(first(), orderedList)));
        // reset:
        removeFirst500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of max 500 elements - first position", size,
                () -> list.addAll(first(), randomList)));
        // reset:
        removeFirst500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - center position", size,
                () -> list.addAll(center(), orderedList)));
        // reset:
        removeCenter500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of max 500 elements - center position", size,
                () -> list.addAll(center(), randomList)));
        // reset:
        removeCenter500(list);


        measurementsHolder.addMeasure(new Measurement(implName, "remove ordered list of 500 elements", size,
                () -> list.removeAll(orderedList)));
        // reset:
        list.addAll(orderedList);
        Collections.sort(list);

        measurementsHolder.addMeasure(new Measurement(implName, "remove random list of max 500 elements", size,
                () -> list.removeAll(randomList)));
        // reset:
        list.addAll(randomList);
        Collections.sort(list);

        measurementsHolder.addMeasure(new Measurement(implName, "retain ordered list of 500 elements", size,
                () -> list.retainAll(orderedList)));


        // Iterator tests
        List<Integer> newOrderedList = listCreator.createOrderedList(size);
        measurementsHolder.addMeasure(new Measurement(implName, "Iterator - iterate all", size,
                () -> {
                    Iterator<Integer> iterator = newOrderedList.iterator();
                    while (iterator.hasNext()) {
                        iterator.next(); // just call
                    }
                }));

        measurementsHolder.addMeasure(new Measurement(implName, "remove local 500 elements - first position", size,
                () -> removeFirst500(newOrderedList)));
        // reset:
        newOrderedList.addAll(first(), orderedList);

        ArrayList<Integer> randomIndexes = HelperUtils.create500RandomIndexesInRange(size);
        measurementsHolder.addMeasure(new Measurement(implName, "remove 500 elements at random index", size,
                () -> randomIndexes.forEach(newOrderedList::remove)));

    }

    private void removeCenter500(List<Integer> list) {
        for (int i = center(); i < center() + 500; i++) {
            list.remove(center());
        }
    }

    private void removeFirst500(List<Integer> list) {
        for (int i = 0; i < 500; i++) {
            list.remove(0);
        }
    }

    private void removeLast500(List<Integer> list) {
        for (int i = size + 499; i >= size; i--) {
            list.remove(i);
        }
    }

    private int center() {
        return size / 2;
    }

    private int last() {
        return size - 1;
    }

    private int first() {
        return 0;
    }

}