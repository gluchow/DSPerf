package de.pgl.collection.measure.measurement.list.creator;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;
import javolution.util.FastTable;
import org.magicwerk.brownies.collections.GapList;

import java.util.List;

public class PersonFastTableCreator extends AbstractListCreator<Person> {

    @Override
    public String getImplName() {
        return "FastTable - Person";
    }

    @Override
    public List<Person> createOrderedList(int size) {
        ensureSizeGreaterZero(size);

        List<Person> result = new FastTable<>(size);
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createPersonWithSuffix(i));
            printCurrentSizeAt100000(i);
        }
        return result;
    }

    @Override
    public List<Person> createRandomList(int size) {
        ensureSizeGreaterZero(size);

        List<Person> result = new GapList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createRandomPerson(size));
            printCurrentSizeAt100000(i);
        }
        return result;
    }

}