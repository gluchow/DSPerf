package de.pgl.collection.measure.measurement.set.creator;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;

import java.util.HashSet;
import java.util.Set;

public class PersonHashSetCreator extends AbstractSetCreator<Person> {

    @Override
    public String getImplName() {
        return "HashSet - Person";
    }

    @Override
    public Set<Person> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Person> result = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createPersonWithSuffix(i));
            printCurrentSizeAt100000(i);
        }
        return result;
    }
}
