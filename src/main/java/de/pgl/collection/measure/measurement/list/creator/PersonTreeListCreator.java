package de.pgl.collection.measure.measurement.list.creator;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;
import org.apache.commons.collections.list.TreeList;

import java.util.List;

/**
 * TreeList Implementierung von apache-commons verwendet AVL-Baum zur Speicherung der Objekte.
 * Ausschnitt aus JavaDoc:
 * ------------- get  add  insert  iterate  remove
 * TreeList       3    5       1       2       1
 * ArrayList      1    1      40       1      40
 * LinkedList  5800    1     350       2     325
 */
public class PersonTreeListCreator extends AbstractListCreator<Person> {

    @Override
    public String getImplName() {
        return "TreeList - Person";
    }

    @Override
    public List<Person> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<Person> result = new TreeList();
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createPersonWithSuffix(i));
            printCurrentSizeAt100000(i);
        }
        return result;
    }

    @Override
    public List<Person> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<Person> result = new TreeList();
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createRandomPerson(size));
            printCurrentSizeAt100000(i);
        }
        return result;
    }

}