package de.pgl.collection.measure;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.measurement.list.ListMeasurements;
import org.apache.commons.collections.list.TreeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static final int ONE_THOUSAND = 1000;
    public static final int HUNDRED_THOUSAND = 100 * ONE_THOUSAND;
    public static final int ONE_MILLION = 1000 * ONE_THOUSAND;
    public static final int TEN_MILLION = 10 * ONE_MILLION;
    public static final int TWENTY_MILLION = 10 * ONE_MILLION;


    public static void main(String[] args) {
        createBigListBeforeTest(TWENTY_MILLION);

        new ListMeasurements(ONE_THOUSAND).performMeasurements();
//        new SetMeasurements(ONE_THOUSAND).performMeasurements();
//
//        new ListMeasurements(HUNDRED_THOUSAND).performMeasurements();
//        new SetMeasurements(HUNDRED_THOUSAND).performMeasurements();
//
//        new ListMeasurements(ONE_MILLION).performMeasurements();
//        new SetMeasurements(ONE_MILLION).performMeasurements();
//
//        new ListMeasurements(TEN_MILLION).performMeasurements();
//        new SetMeasurements(TEN_MILLION).performMeasurements();

//        testTreeMap();
//        testHashMap();
//        testTreeList();
    }

    private static void testTreeList() {
        System.out.println("TreeList Test - Start");
        long start = currentTime();
        TreeList list = new TreeList();
        for (int i = 0; i < TEN_MILLION; i++) {
            list.add(createPersonWithAge20(i));
            if (i % 100000 == 0) {
                System.out.println("Aktuell bei: " + i);
            }
        }
        long duration = currentTime() - start;
        System.out.println("Created in: " + duration + "ms");

        start = currentTime();
        int index = list.indexOf(createPersonWithAge20(123456));
        Person found = (Person) list.get(index);
        duration = currentTime() - start;
        System.out.println("Found: " + found);
        System.out.println("duration to find: " + duration + "ms");
        System.out.println("size: " + list.size());
        System.out.println("TreeList Test - End");
    }

    private static void testTreeMap() {
        System.out.println("TreeMap Test - Start");
        long start = currentTime();
        Map<Person, Integer> map = new TreeMap<>();
        for (int i = 0; i < TEN_MILLION; i++) {
            map.put(createPersonWithRandomAge(i), i);
            if (i % 10000 == 0) {
                System.out.println("Aktuell bei: " + i);
            }
        }
        long duration = currentTime() - start;
        System.out.println("Created in: " + duration + "ms");

        Person toFind = createPersonWithRandomAge(50);
        start = currentTime();
        Integer found = map.get(toFind);
        duration = currentTime() - start;
        System.out.println("Found: " + found);
        System.out.println("duration to find: " + duration + "ms");
        System.out.println("size: " + map.size());
        System.out.println("TreeMap Test - End");
    }

    private static void testHashMap() {
        System.out.println("HashMap Test - Start");
        long start = currentTime();
        Map<Person, Integer> map = new HashMap<>();
        for (int i = 0; i < TEN_MILLION; i++) {
            map.put(createPersonWithRandomAge(i), i);
            if (i % 10000 == 0) {
                System.out.println("Aktuell bei: " + i);
            }
        }
        long duration = currentTime() - start;
        System.out.println("Created in: " + duration + "ms");

        Person toFind = createPersonWithRandomAge(50);
        start = currentTime();
        Integer found = map.get(toFind);
        duration = currentTime() - start;
        System.out.println("Found: " + found);
        System.out.println("duration to find: " + duration + "ms");
        System.out.println("size: " + map.size());
        System.out.println("HashMap Test - End");
    }

    private static long currentTime() {
        return System.currentTimeMillis();
    }

    private static Person createPersonWithRandomAge(int i) {
        return new Person(stringWith("name" + i), stringWith("email" + i), stringWith("address" + i));
    }

    private static Person createPersonWithAge20(int i) {
        return new Person(stringWith("name" + i), stringWith("email" + i), stringWith("address" + i));
    }

    private static void createBigListBeforeTest(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    private static String stringWith(String suffix) {
        return "It is a long String with following suffix...:" + suffix;
    }

}
