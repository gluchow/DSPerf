package de.pgl.collection.measure;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.custom_hash.TObjectIntCustomHashMap;
import gnu.trove.map.hash.TCustomHashMap;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.set.hash.TLinkedHashSet;
import gnu.trove.strategy.IdentityHashingStrategy;
import org.magicwerk.brownies.collections.BigList;

import java.util.*;

public class MemoryTests {
    //you need to allocate 1.7G+ heap for this constant
    private static final int SIZE = 1000 * 1000;
    private static final float FILL_FACTOR = 0.75f;

    public static void main(String[] args) throws IllegalAccessException {

        //testing sets memory consumption
        testJdkHashSet();
        testJdkLinkedHashSet();
        testTHashSet();
        testTLinkedHashSet();
        testTIntHashSet();

        //testing map memory consumption
        testJdkHashMap();
        testJdkIdentityHashMap();
        testTroveIdentityHashMap();

        //testing numeric collections memory consumption
        testJdkIntIntHashMap();
        testTroveIntIntHashMap();
        testJdkIntHashSet();
        testTroveIntHashSet();
        testJdkIntLinkedHashSet();
        testTroveIntLinkedHashSet();

        //lists
        testTroveArrayList();
        testJdkArrayList();
        testBigList();
    }

    private static void testBigList() {
        final long start = getUsedMemory();
        final List<Integer> list = new BigList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            list.add(i);
            if(i % 1000000 == 0)
                System.out.println("BigList " + i);
        }
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("BigList= " + size + 'M');
        if (list.size() == 1) System.out.println(1);
    }

    private static void testJdkArrayList() {
        final long start = getUsedMemory();
        final List<Integer> list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            list.add(i);
            if(i % 1000000 == 0)
                System.out.println("ArrayList " + i);
        }
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("ArrayList= " + size + 'M');
        if (list.size() == 1) System.out.println(1);
    }

    private static void testTroveArrayList() {
        final long start = getUsedMemory();
        final TIntArrayList list = new TIntArrayList(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            list.add(i);
            if(i % 1000000 == 0)
                System.out.println("TIntArrayList " + i);
        }
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TIntArrayList= " + size + 'M');
        if (list.size() == 1) System.out.println(1);
    }

    private static void testJdkIntIntHashMap() {
        final long start = getUsedMemory();
        final Map<Integer, Integer> map = new HashMap<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put(i, i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("HashMap<Integer, Integer> = " + size + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testTroveIntIntHashMap() {
        final long start = getUsedMemory();
        final TIntIntMap map = new TIntIntHashMap(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put(i, i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TIntIntHashMap = " + size + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testJdkIntHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new HashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("HashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTroveIntHashSet() {
        final long start = getUsedMemory();
        final TIntSet set = new TIntHashSet(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TIntHashSet = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testJdkIntLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new LinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("LinkedHashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTroveIntLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new TLinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TLinkedHashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static long getUsedMemory() {
        System.gc();
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private static void testJdkHashMap() {
        final long start = getUsedMemory();
        final Map<String, Integer> map = new HashMap<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("HashMap<String, Integer> = " + size + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testJdkIdentityHashMap() {
        final long start = getUsedMemory();
        final Map<String, Integer> map = new IdentityHashMap<>(SIZE);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("IdentityHashMap<String, Integer> = " + size + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testTroveIdentityHashMap() {
        final long start = getUsedMemory();
        //INSTANCE is available from Trove 3.1a1
        final TObjectIntCustomHashMap<String> map = new TObjectIntCustomHashMap<>(IdentityHashingStrategy.INSTANCE, SIZE, FILL_FACTOR, -1);
        final TCustomHashMap<String, String> map2 = new TCustomHashMap<>(IdentityHashingStrategy.INSTANCE);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TObjectIntCustomHashMap<String> = " + size + 'M');
        if (map.size() == 1) System.out.println(1);
    }


    private static void testJdkHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new HashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("HashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testJdkLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new LinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("LinkedHashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new THashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("THashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new TLinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TLinkedHashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTIntHashSet() {
        final long start = getUsedMemory();
        final TIntSet set = new TIntHashSet(SIZE, FILL_FACTOR, -1);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        final long size = (getUsedMemory() - start) / 1024 / 1024;
        System.out.println("TIntHashSet<Integer> = " + size + 'M');
        if (set.size() == 1) System.out.println(1);
    }
}
