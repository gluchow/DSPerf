package de.pgl.collection.measure;

import de.pgl.collection.measure.measurement.list.IntegerListMeasurements;
import de.pgl.collection.measure.measurement.list.StringListMeasurements;
import de.pgl.collection.measure.measurement.set.IntegerSetMeasurements;

import java.util.ArrayList;

public class Main {

    public static final int HUNDRED_THOUSAND = 100000;
    public static final int ONE_MILLION = 1000000;
    public static final int TEN_MILLION = 10000000;
    public static final int HUNDRED_MILLION = 100000000;

    public static void main(String[] args) {
        createBigListBeforeTest(TEN_MILLION);

//        new IntegerListMeasurements(ONE_MILLION).performMeasurements();
//        new StringListMeasurements(ONE_MILLION).performMeasurements();
        new IntegerSetMeasurements(ONE_MILLION).performMeasurements();
    }

    private static void createBigListBeforeTest(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

}
