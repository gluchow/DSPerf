package de.pgl.collection.measure;

import de.pgl.collection.measure.measurement.list.ListMeasurements;

public class Main {

    public static final int HUNDRED_THOUSAND = 100000;
    public static final int ONE_MILLION = 1000000;
    public static final int TEN_MILLION = 10000000;
    public static final int HUNDRED_MILLION = 100000000;

    public static void main(String[] args) {
        new ListMeasurements(HUNDRED_THOUSAND).performMeasurements();
        new ListMeasurements(ONE_MILLION).performMeasurements();
    }

}
