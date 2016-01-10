package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListMeasurements {

    public static void main(String[] args) {
        createBigListBeforeTest(100000);

        MeasurementsHolder measurementsHolder = new MeasurementsHolder();

        for (int i = 0; i < 10; i++) {
            proceedListOperations("ArrayList", measurementsHolder, new IntegerArrayListCreator());
            proceedListOperations("LinkedList", measurementsHolder, new IntegerLinkedListCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getMinDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getAvgDurations());
        MeasurementsWriter.writeMeasurements(measurementsHolder.getMaxDurations());
    }

    private static void proceedListOperations(String implName, MeasurementsHolder holder, ListCreator<Integer> listCreator) {
        int size = 1000000;
        int highestInt = size - 1;

        holder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<Integer> list = listCreator.createOrderedList(size);

        holder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(randomInt(highestInt))));
        holder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(0)));
        holder.addMeasure(new Measurement(implName, "find", size,
                () -> list.indexOf(highestInt)));

        holder.addMeasure(new Measurement(implName, "get", size,
                () -> list.indexOf(randomInt(highestInt))));
        holder.addMeasure(new Measurement(implName, "get", size,
                () -> list.indexOf(0)));
        holder.addMeasure(new Measurement(implName, "get", size,
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