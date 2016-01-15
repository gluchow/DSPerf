package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.*;

import java.util.List;
import java.util.Random;

public class ListMeasurements {
    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public ListMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new ArrayListCreator());
            proceedListOperations(new LinkedListCreator());
            proceedListOperations(new GapListCreator());
            proceedListOperations(new BigListCreator());
            proceedListOperations(new TreeListCreator());
            proceedListOperations(new FastTableCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getDurations());
//        MeasurementsWriter.writeMeasurements(measurementsHolder.getAvgDurations());
//        MeasurementsWriter.writeMeasurements(measurementsHolder.getMaxDurations());
    }

    private void proceedListOperations(ListCreator<Integer> listCreator) {
        String implName = listCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<Integer> list = listCreator.createOrderedList(size);

        measurementsHolder.addMeasure(new Measurement(implName, "find_first", size,
                () -> list.indexOf(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_random", size,
                () -> list.indexOf(random())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_last", size,
                () -> list.indexOf(last())));

        measurementsHolder.addMeasure(new Measurement(implName, "get_first", size,
                () -> list.get(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_random", size,
                () -> list.get(random())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_last", size,
                () -> list.get(last())));

        measurementsHolder.addMeasure(new Measurement(implName, "add_first", size,
                () -> list.add(last())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_random", size,
                () -> list.add(random(), last())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_last", size,
                () -> list.add(first(), last())));

        measurementsHolder.addMeasure(new Measurement(implName, "remove_first", size,
                () -> list.remove(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_random", size,
                () -> list.remove(random())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_last", size,
                () -> list.remove(last())));

    }

    private int random() {
        return randomInt(last());
    }

    private int last() {
        return size - 1;
    }

    private int first() {
        return 0;
    }

    private static int randomInt(int max) {
        return new Random().nextInt(max);
    }

}