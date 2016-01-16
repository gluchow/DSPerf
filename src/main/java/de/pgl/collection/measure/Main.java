package de.pgl.collection.measure;

import de.pgl.collection.measure.measurement.list.IntegerListMeasurements;
import de.pgl.collection.measure.measurement.list.PersonListMeasurements;
import de.pgl.collection.measure.measurement.set.IntegerSetMeasurements;
import de.pgl.collection.measure.measurement.set.PersonSetMeasurements;

import java.util.ArrayList;

public class Main {

    public static final int ONE_THOUSAND = 1000;
    public static final int HUNDRED_THOUSAND = 100 * ONE_THOUSAND;
    public static final int ONE_MILLION = 1000 * ONE_THOUSAND;
    public static final int FIVE_MILLION = 5 * ONE_MILLION;
    public static final int TEN_MILLION = 10 * ONE_MILLION;


    public static void main(String[] args) {
        createBigListBeforeTest(ONE_MILLION);

        runTestsWithSize(ONE_THOUSAND);
        runTestsWithSize(HUNDRED_THOUSAND);
        runTestsWithSize(ONE_MILLION);
        runTestsWithSize(FIVE_MILLION);
    }

    private static void runTestsWithSize(int size) {
        new IntegerListMeasurements(size).performMeasurements();
        new PersonListMeasurements(size).performMeasurements();
        new IntegerSetMeasurements(size).performMeasurements();
        new PersonSetMeasurements(size).performMeasurements();
    }

    private static void createBigListBeforeTest(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

}
