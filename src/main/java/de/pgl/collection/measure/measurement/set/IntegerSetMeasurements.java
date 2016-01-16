package de.pgl.collection.measure.measurement.set;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.set.creator.IntegerFastSetCreator;
import de.pgl.collection.measure.measurement.set.creator.IntegerHashSetCreator;
import de.pgl.collection.measure.measurement.set.creator.IntegerTreeSetCreator;
import de.pgl.collection.measure.measurement.set.creator.SetCreator;

import java.util.*;

public class IntegerSetMeasurements {

    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public IntegerSetMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new IntegerTreeSetCreator());
            proceedListOperations(new IntegerHashSetCreator());
            proceedListOperations(new IntegerFastSetCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getDurations());
    }

    private void proceedListOperations(SetCreator<Integer> setCreator) {
        String implName = setCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> setCreator.createSet(size)));

        Set<Integer> set = setCreator.createSet(size);

        measurementsHolder.addMeasure(new Measurement(implName, "contains", size,
                () -> set.contains(random())));

        measurementsHolder.addMeasure(new Measurement(implName, "remove", size,
                () -> set.remove(center())));
        // reset:
        set.add(center());

        // Mengenoperationen
        List<Integer> listWithNegatives500 = createListWithNegatives500();
        List<Integer> listWithExistingElements500 = createListWithExistingElements500();

        measurementsHolder.addMeasure(new Measurement(implName, "contains all for 500 not existing elements", size,
                () -> set.containsAll(listWithNegatives500)));
        measurementsHolder.addMeasure(new Measurement(implName, "contains all for 500 existing elements", size,
                () -> set.containsAll(listWithExistingElements500)));

        measurementsHolder.addMeasure(new Measurement(implName, "add 500 elements (all new, negatives)", size,
                () -> set.addAll(listWithNegatives500)));
        // reset:
        set.removeAll(listWithNegatives500);

        measurementsHolder.addMeasure(new Measurement(implName, "add 500 already existing elements", size,
                () -> set.addAll(listWithExistingElements500)));
        // no reset needed

        measurementsHolder.addMeasure(new Measurement(implName, "remove all for 500 not existing elements", size,
                () -> set.removeAll(listWithNegatives500)));
        measurementsHolder.addMeasure(new Measurement(implName, "remove all for 500 existing elements", size,
                () -> set.removeAll(listWithExistingElements500)));
        // reset:
        set.addAll(listWithExistingElements500);

        measurementsHolder.addMeasure(new Measurement(implName, "retain all for 500 not existing elements", size,
                () -> set.retainAll(listWithNegatives500)));
        // set should be empty. reset:
        set.addAll(setCreator.createSet(size));

        measurementsHolder.addMeasure(new Measurement(implName, "retain all for 500 existing elements", size,
                () -> set.retainAll(listWithExistingElements500)));
        // reset
        set.addAll(setCreator.createSet(size));

        // Iterator
        measurementsHolder.addMeasure(new Measurement(implName, "Iterator - iterate all", size,
                () -> {
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        iterator.next(); // just call
                    }
                }));

        measurementsHolder.addMeasure(new Measurement(implName, "Iterator - iterate all and remove every every hundredth", size,
                () -> {
                    Iterator<Integer> iterator = set.iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        i++;
                        iterator.next();
                        if (i % 100 == 0) iterator.remove();
                    }
                }));

    }

    private int center() {
        return size / 2;
    }

    private List<Integer> createListWithExistingElements500() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            result.add(i);
        }
        return result;
    }

    private List<Integer> createListWithNegatives500() {
        List<Integer> result = new ArrayList<>();
        for (int i = -1; i > -500; i--) {
            result.add(i);
        }
        return result;
    }

    private int random() {
        return new Random().nextInt(size);
    }
}
