package de.pgl.collection.measure.measurement.list.creator;

import org.apache.commons.collections.list.TreeList;

import java.util.List;
import java.util.Random;

/**
 * TreeList Implementierung von apache-commons verwendet AVL-Baum zur Speicherung der Objekte.
 * Ausschnitt aus JavaDoc:
 * ------------- get  add  insert  iterate  remove
 * TreeList       3    5       1       2       1
 * ArrayList      1    1      40       1      40
 * LinkedList  5800    1     350       2     325
 */
public class TreeListCreator extends AbstractListCreator<Integer> {

    @Override
    public String getImplName() {
        return "TreeList";
    }

    @Override
    public List<Integer> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new TreeList();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    @Override
    public List<Integer> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<Integer> result = new TreeList();
        for (int i = 0; i < size; i++) {
            result.add(new Random().nextInt(size));
        }
        return result;
    }

}