package de.pgl.collection.measure.measurement.list;

import java.util.ArrayList;
import java.util.Random;

public final class HelperUtils {

    public static ArrayList<Integer> create500RandomIndexesInRange(int range) {
        ArrayList<Integer> randomIndexes = new ArrayList<>(500);
        int count = 0;
        while (count < 500) {
            int nextRandom = new Random().nextInt(range);
            if (!randomIndexes.contains(nextRandom)) {
                randomIndexes.add(nextRandom);
                count++;
            }
        }
        return randomIndexes;
    }
}
