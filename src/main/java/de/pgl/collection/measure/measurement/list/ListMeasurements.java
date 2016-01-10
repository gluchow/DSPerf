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

        for(int i=0; i<10;i++) {
            proceedArrayListOperations();
            proceedTreeListOperations();
        }

        MeasurementsWriter.writeMeasurements(MeasurementsHolder.getMinDurations());
        MeasurementsWriter.writeMeasurements(MeasurementsHolder.getAvgDurations());
        MeasurementsWriter.writeMeasurements(MeasurementsHolder.getMaxDurations());

        MeasurementsWriter.close();
    }

    private static void proceedTreeListOperations() {
        int size = 2000000;
        int highestInt = size - 1;

        String implName = "LinkedList";
        Measurement createMeasure = new Measurement(implName, "create", size);
        createMeasure.executeAndHoldResult(() -> IntegerListCreateHelper.createOrderedLinkedList(size));

        List<Integer> list = IntegerListCreateHelper.createOrderedLinkedList(size);

        Measurement findMeasure = new Measurement(implName, "find", size);
        findMeasure.executeAndHoldResult(() -> list.indexOf(randomInt(highestInt)));
        findMeasure.executeAndHoldResult(() -> list.indexOf(0));
        findMeasure.executeAndHoldResult(() -> list.indexOf(highestInt));

        Measurement getMeasure = new Measurement(implName, "get", size);
        getMeasure.executeAndHoldResult(() -> list.indexOf(randomInt(highestInt)));
        getMeasure.executeAndHoldResult(() -> list.indexOf(0));
        getMeasure.executeAndHoldResult(() -> list.indexOf(highestInt));
    }

    private static void proceedArrayListOperations() {
        int size = 1000000;
        int highestInt = size - 1;

        String implName = "ArrayList";

        Measurement createMeasure = new Measurement(implName, "create", size);
        createMeasure.executeAndHoldResult(() -> IntegerListCreateHelper.createOrderedArrayList(size));

        List<Integer> list = IntegerListCreateHelper.createOrderedArrayList(size);

        Measurement findMeasure = new Measurement(implName, "find", size);
        findMeasure.executeAndHoldResult(() -> list.indexOf(randomInt(highestInt)));
        findMeasure.executeAndHoldResult(() -> list.indexOf(0));
        findMeasure.executeAndHoldResult(() -> list.indexOf(highestInt));

        Measurement getMeasure = new Measurement(implName, "get", size);
        getMeasure.executeAndHoldResult(() -> list.indexOf(randomInt(highestInt)));
        getMeasure.executeAndHoldResult(() -> list.indexOf(0));
        getMeasure.executeAndHoldResult(() -> list.indexOf(highestInt));
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