package de.pgl.collection.measure.measurement.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IntegerListCreateHelper {

    public static List<Integer> createOrderedArrayList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    public static List<Integer> createRandomArrayList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Random().nextInt());
        }
        return result;
    }

    public static List<Integer> createOrderedLinkedList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    public static List<Integer> createRandomLinkedList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add(new Random().nextInt());
        }
        return result;
    }

    private static void ensureSizeGreaterZero(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than zero.");
        }
    }
}
