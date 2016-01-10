package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringListMeasurements {
    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder("String");

    public StringListMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        createBigListBeforeTest(1000000);

        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new StringArrayListCreator());
            proceedListOperations(new StringLinkedListCreator());
            proceedListOperations(new StringGapListCreator());
            proceedListOperations(new StringBigListCreator());
            proceedListOperations(new StringTreeListCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getMinDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getAvgDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getMaxDurations());
    }

    private void proceedListOperations(ListCreator<String> listCreator) {
        String implName = listCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<String> list = listCreator.createOrderedList(size);

        measurementsHolder.addMeasure(new Measurement(implName, "find_first", size,
                () -> list.indexOf(firstString())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_random", size,
                () -> list.indexOf(randomString())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_last", size,
                () -> list.indexOf(lastString())));

        measurementsHolder.addMeasure(new Measurement(implName, "get_first", size,
                () -> list.get(first())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_random", size,
                () -> list.get(random())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_last", size,
                () -> list.get(last())));

        measurementsHolder.addMeasure(new Measurement(implName, "add_first", size,
                () -> list.add(lastString())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_random", size,
                () -> list.add(random(), lastString())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_last", size,
                () -> list.add(first(), lastString())));

        measurementsHolder.addMeasure(new Measurement(implName, "remove_first", size,
                () -> list.remove(firstString())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_random", size,
                () -> list.remove(randomString())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_last", size,
                () -> list.remove(lastString())));
    }

    private String randomString() {
        return "" + (randomInt(last()));
    }

    private String lastString() {
        return "" + (size - 1);
    }

    private String firstString() {
        return "" + 0;
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

    private static void createBigListBeforeTest(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

}