package de.pgl.collection.measure;

import org.magicwerk.brownies.collections.BigList;
import org.magicwerk.brownies.collections.GapList;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        //proceedFakeAdds();

        long[] measures = new long[]{0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 10; i++) {
            measures[0] += measureAdd(new ArrayList<>());
            measures[1] += measureAdd(new BigList<>());
            measures[2] += measureAdd(new LinkedList<>());
            measures[3] += measureAdd(new Stack<>());
            measures[4] += measureAdd(new Vector<>());
            measures[5] += measureAdd(new GapList<>());
        }

        System.out.println("ArrayList: " + measures[0]);
        System.out.println("BigList: " + measures[1]);
        System.out.println("LinkedList: " + measures[2]);
        System.out.println("Stack: " + measures[3]);
        System.out.println("Vector: " + measures[4]);
        System.out.println("GapList: " + measures[5]);
    }

    private static void proceedFakeAdds() {
        measureAdd(new ArrayList<>());
        measureAdd(new ArrayList<>());
    }

    private static long measureAdd(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
