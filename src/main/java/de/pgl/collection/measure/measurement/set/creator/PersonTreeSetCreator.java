package de.pgl.collection.measure.measurement.set.creator;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;

import java.util.Set;
import java.util.TreeSet;

public class PersonTreeSetCreator extends AbstractSetCreator<Person> {

    @Override
    public String getImplName() {
        return "TreeSet - Person";
    }

    @Override
    public Set<Person> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Person> result = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createPersonWithSuffix(i));
            printCurrentSizeAt100000(i);
        }
        return result;
    }
}
