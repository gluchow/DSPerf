package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListMeasurements {
    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public ListMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        createBigListBeforeTest(1000000);

        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new IntegerArrayListCreator());
            proceedListOperations(new IntegerLinkedListCreator());
            proceedListOperations(new IntegerGapListCreator());
            proceedListOperations(new IntegerBigListCreator());
            proceedListOperations(new IntegerTreeListCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getMinDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getAvgDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getMaxDurations());
    }

    private void proceedListOperations(ListCreator<Integer> listCreator) {
        int highestInt = size - 1;

        String implName = listCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<Integer> list = listCreator.createOrderedList(size);

        measurementsHolder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(randomInt(highestInt))));
        measurementsHolder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(0)));
        measurementsHolder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(highestInt)));

        measurementsHolder.addMeasure(new Measurement(implName, "get", size,
                () -> list.indexOf(randomInt(highestInt))));
        measurementsHolder.addMeasure(new Measurement(implName, "get", size,
                () -> list.indexOf(0)));
        measurementsHolder.addMeasure(new Measurement(implName, "get", size,
                () -> list.indexOf(highestInt)));
    }

    private static int randomInt(int max) {
        return new Random().nextInt(max);
    }

    private static void createBigListBeforeTest(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

}